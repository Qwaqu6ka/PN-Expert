package ru.fefu.video_tests_impl.presentation

import android.content.Context
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View


/**
 * Base class for a custom graphics object to be rendered within the graphic overlay. Subclass
 * this and implement the [Graphic.draw] method to define the graphics element.
 */
abstract class Graphic(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    // Matrix for transforming from image coordinates to overlay view coordinates.
    private val transformationMatrix = Matrix()
    var imageWidth = 0
        private set
    var imageHeight = 0
        private set

    // The factor of overlay View size to image size. Anything in the image coordinates need to be
    // scaled by this amount to fit with the area of overlay View.
    private var scaleFactor = 1.0f

    // The number of horizontal pixels needed to be cropped on each side to fit the image with the
    // area of overlay View after scaling.
    private var postScaleWidthOffset = 0f

    // The number of vertical pixels needed to be cropped on each side to fit the image with the
    // area of overlay View after scaling.
    private var postScaleHeightOffset = 0f
    private var isImageFlipped = false
    private var needUpdateTransformation = true

    init {
        addOnLayoutChangeListener { _: View?, _: Int, _: Int, _: Int, _: Int, _: Int, _: Int, _: Int, _: Int ->
            needUpdateTransformation = true
        }
    }

    /**
     * Draw the graphic on the supplied canvas. Drawing should use the following methods to convert
     * to view coordinates for the graphics that are drawn:
     *
     *
     *  1. [Graphic.scale] adjusts the size of the supplied value from the image
     * scale to the view scale.
     *  1. [Graphic.translateX] and [Graphic.translateY] adjust the
     * coordinate from the image's coordinate system to the view coordinate system.
     *
     *
     * @param canvas drawing canvas
     */
    abstract override fun draw(canvas: Canvas)

    /**
     * Sets the source information of the image being processed by detectors, including size and
     * whether it is flipped, which informs how to transform image coordinates later.
     *
     * @param imageWidth the width of the image sent to ML Kit detectors
     * @param imageHeight the height of the image sent to ML Kit detectors
     * @param isFlipped whether the image is flipped. Should set it to true when the image is from the
     * front camera.
     */
    fun setImageSourceInfo(imageWidth: Int, imageHeight: Int, isFlipped: Boolean) {
        if (imageHeight <= 0 || imageWidth <= 0) {
            throw IllegalArgumentException("imageHeight and imageWidth must be > 0\nimageHeight = $imageHeight  imageWidth = $imageWidth")
        }
        this.imageWidth = imageWidth
        this.imageHeight = imageHeight
        isImageFlipped = isFlipped
        needUpdateTransformation = true
        postInvalidate()
    }

    /** Adjusts the supplied value from the image scale to the view scale.  */
    private fun scale(imagePixel: Float): Float {
        return imagePixel * scaleFactor
    }

    fun isImageFlipped(): Boolean {
        return isImageFlipped
    }

    /**
     * Adjusts the x coordinate from the image's coordinate system to the view coordinate system.
     */
    protected fun translateX(x: Float): Float {
        return if (isImageFlipped) {
            width - (scale(x) - postScaleWidthOffset)
        } else {
            scale(x) - postScaleWidthOffset
        }
    }

    /**
     * Adjusts the y coordinate from the image's coordinate system to the view coordinate system.
     */
    protected fun translateY(y: Float): Float {
        return scale(y) - postScaleHeightOffset
    }

    /**
     * Returns a [Matrix] for transforming from image coordinates to overlay view coordinates.
     */
    fun getTransformationMatrix(): Matrix {
        return transformationMatrix
    }

    /**
     * Given the `zInImagePixel`, update the color for the passed in `paint`. The color will be
     * more red if the `zInImagePixel` is smaller, or more blue ish vice versa. This is
     * useful to visualize the z value of landmarks via color for features like Pose and Face Mesh.
     *
     * @param paint the paint to update color with
     * @param canvas the canvas used to draw with paint
     * @param visualizeZ if true, paint color will be changed.
     * @param rescaleZForVisualization if true, re-scale the z value with zMin and zMax to make
     * color more distinguishable
     * @param zInImagePixel the z value used to update the paint color
     * @param zMin min value of all z values going to be passed in
     * @param zMax max value of all z values going to be passed in
     */
    fun updatePaintColorByZValue(
        paint: Paint,
        canvas: Canvas,
        visualizeZ: Boolean,
        rescaleZForVisualization: Boolean,
        zInImagePixel: Float,
        zMin: Float,
        zMax: Float
    ) {
        if (!visualizeZ) {
            return
        }

        // When visualizeZ is true, sets up the paint to different colors based on z values.
        // Gets the range of z value.
        val zLowerBoundInScreenPixel: Float
        val zUpperBoundInScreenPixel: Float
        if (rescaleZForVisualization) {
            zLowerBoundInScreenPixel = (-0.001f).coerceAtMost(scale(zMin))
            zUpperBoundInScreenPixel = 0.001f.coerceAtLeast(scale(zMax))
        } else {
            // By default, assume the range of z value in screen pixel is [-canvasWidth, canvasWidth].
            val defaultRangeFactor = 1f
            zLowerBoundInScreenPixel = -defaultRangeFactor * canvas.width
            zUpperBoundInScreenPixel = defaultRangeFactor * canvas.width
        }
        val zInScreenPixel = scale(zInImagePixel)
        if (zInScreenPixel < 0) {
            // Sets up the paint to be red if the item is in front of the z origin.
            // Maps values within [zLowerBoundInScreenPixel, 0) to [255, 0) and use it to control the
            // color. The larger the value is, the more red it will be.
            var v = (zInScreenPixel / zLowerBoundInScreenPixel * 255).toInt()
            v = constrainToRange(v, 0, 255)
            paint.setARGB(255, 255, 255 - v, 255 - v)
        } else {
            // Sets up the paint to be blue if the item is behind the z origin.
            // Maps values within [0, zUpperBoundInScreenPixel] to [0, 255] and use it to control the
            // color. The larger the value is, the more blue it will be.
            var v = (zInScreenPixel / zUpperBoundInScreenPixel * 255).toInt()
            v = constrainToRange(v, 0, 255)
            paint.setARGB(255, 255 - v, 255 - v, 255)
        }
    }

    private fun constrainToRange(value: Int, lowerBound: Int, upperBound: Int): Int {
        return if (value < lowerBound) {
            lowerBound
        } else if (value > upperBound) {
            upperBound
        } else {
            value
        }
    }

    private fun updateTransformationIfNeeded() {
        if (!needUpdateTransformation || imageWidth <= 0 || imageHeight <= 0) {
            return
        }
        val viewAspectRatio = width.toFloat() / height
        val imageAspectRatio = imageWidth.toFloat() / imageHeight
        postScaleWidthOffset = 0f
        postScaleHeightOffset = 0f
        if (viewAspectRatio > imageAspectRatio) {
            // The image needs to be vertically cropped to be displayed in this view.
            scaleFactor = width.toFloat() / imageWidth
            postScaleHeightOffset = (width.toFloat() / imageAspectRatio - height) / 2
        } else {
            // The image needs to be horizontally cropped to be displayed in this view.
            scaleFactor = height.toFloat() / imageHeight
            postScaleWidthOffset = (height.toFloat() * imageAspectRatio - width) / 2
        }
        transformationMatrix.reset()
        transformationMatrix.setScale(scaleFactor, scaleFactor)
        transformationMatrix.postTranslate(-postScaleWidthOffset, -postScaleHeightOffset)
        if (isImageFlipped) {
            transformationMatrix.postScale(-1f, 1f, width / 2f, height / 2f)
        }
        needUpdateTransformation = false
    }
}
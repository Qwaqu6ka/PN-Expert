package ru.fefu.video_tests_impl.presentation

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.drawable.Drawable
import com.google.mlkit.vision.pose.Pose
import com.google.mlkit.vision.pose.PoseLandmark
import java.util.Locale

/** Draw the detected pose in preview.
 * @param pose Pose to analyze
 * @param showInFrameLikelihood Displays InFrameLikelihood score for each landmark
 * @param visualizeZ Uses different colors to indicate z difference (red: smaller z, blue: larger z)
 * @param rescaleZForVisualization Maps the smallest z value to the most red and the largest z value to the most blue. This makes z difference more obvious
 * */
internal class PoseDrawable(
    private val pose: Pose,
    private val showInFrameLikelihood: Boolean,
    private val visualizeZ: Boolean,
    private val rescaleZForVisualization: Boolean
) : Drawable() {
    private var zMin = Float.MAX_VALUE
    private var zMax = Float.MIN_VALUE
    private val leftPaint: Paint = Paint()
    private val rightPaint: Paint = Paint()
    private val whitePaint: Paint = Paint()

    init {
        whitePaint.strokeWidth = STROKE_WIDTH
        whitePaint.color = Color.WHITE
        whitePaint.textSize = IN_FRAME_LIKELIHOOD_TEXT_SIZE
        leftPaint.strokeWidth = STROKE_WIDTH
        leftPaint.color = Color.GREEN
        rightPaint.strokeWidth = STROKE_WIDTH
        rightPaint.color = Color.YELLOW
    }

    override fun draw(p0: Canvas) {

        val landmarks = pose.allPoseLandmarks
        if (landmarks.isEmpty()) {
            return
        }

        // Draw all the points
        for (landmark in landmarks) {
            drawPoint(p0, landmark, whitePaint)
            if (visualizeZ && rescaleZForVisualization) {
                zMin = zMin.coerceAtMost(landmark.position3D.z)
                zMax = zMax.coerceAtLeast(landmark.position3D.z)
            }
        }

        val nose = pose.getPoseLandmark(PoseLandmark.NOSE)
        val leftEyeInner = pose.getPoseLandmark(PoseLandmark.LEFT_EYE_INNER)
        val leftEye = pose.getPoseLandmark(PoseLandmark.LEFT_EYE)
        val leftEyeOuter = pose.getPoseLandmark(PoseLandmark.LEFT_EYE_OUTER)
        val rightEyeInner = pose.getPoseLandmark(PoseLandmark.RIGHT_EYE_INNER)
        val rightEye = pose.getPoseLandmark(PoseLandmark.RIGHT_EYE)
        val rightEyeOuter = pose.getPoseLandmark(PoseLandmark.RIGHT_EYE_OUTER)
        val leftEar = pose.getPoseLandmark(PoseLandmark.LEFT_EAR)
        val rightEar = pose.getPoseLandmark(PoseLandmark.RIGHT_EAR)
        val leftMouth = pose.getPoseLandmark(PoseLandmark.LEFT_MOUTH)
        val rightMouth = pose.getPoseLandmark(PoseLandmark.RIGHT_MOUTH)

        val leftShoulder = pose.getPoseLandmark(PoseLandmark.LEFT_SHOULDER)
        val rightShoulder = pose.getPoseLandmark(PoseLandmark.RIGHT_SHOULDER)
        val leftElbow = pose.getPoseLandmark(PoseLandmark.LEFT_ELBOW)
        val rightElbow = pose.getPoseLandmark(PoseLandmark.RIGHT_ELBOW)
        val leftWrist = pose.getPoseLandmark(PoseLandmark.LEFT_WRIST)
        val rightWrist = pose.getPoseLandmark(PoseLandmark.RIGHT_WRIST)
        val leftHip = pose.getPoseLandmark(PoseLandmark.LEFT_HIP)
        val rightHip = pose.getPoseLandmark(PoseLandmark.RIGHT_HIP)
        val leftKnee = pose.getPoseLandmark(PoseLandmark.LEFT_KNEE)
        val rightKnee = pose.getPoseLandmark(PoseLandmark.RIGHT_KNEE)
        val leftAnkle = pose.getPoseLandmark(PoseLandmark.LEFT_ANKLE)
        val rightAnkle = pose.getPoseLandmark(PoseLandmark.RIGHT_ANKLE)

        val leftPinky = pose.getPoseLandmark(PoseLandmark.LEFT_PINKY)
        val rightPinky = pose.getPoseLandmark(PoseLandmark.RIGHT_PINKY)
        val leftIndex = pose.getPoseLandmark(PoseLandmark.LEFT_INDEX)
        val rightIndex = pose.getPoseLandmark(PoseLandmark.RIGHT_INDEX)
        val leftThumb = pose.getPoseLandmark(PoseLandmark.LEFT_THUMB)
        val rightThumb = pose.getPoseLandmark(PoseLandmark.RIGHT_THUMB)
        val leftHeel = pose.getPoseLandmark(PoseLandmark.LEFT_HEEL)
        val rightHeel = pose.getPoseLandmark(PoseLandmark.RIGHT_HEEL)
        val leftFootIndex = pose.getPoseLandmark(PoseLandmark.LEFT_FOOT_INDEX)
        val rightFootIndex = pose.getPoseLandmark(PoseLandmark.RIGHT_FOOT_INDEX)

        // Face
        drawLine(p0, nose, leftEyeInner, whitePaint)
        drawLine(p0, leftEyeInner, leftEye, whitePaint)
        drawLine(p0, leftEye, leftEyeOuter, whitePaint)
        drawLine(p0, leftEyeOuter, leftEar, whitePaint)
        drawLine(p0, nose, rightEyeInner, whitePaint)
        drawLine(p0, rightEyeInner, rightEye, whitePaint)
        drawLine(p0, rightEye, rightEyeOuter, whitePaint)
        drawLine(p0, rightEyeOuter, rightEar, whitePaint)
        drawLine(p0, leftMouth, rightMouth, whitePaint)

        drawLine(p0, leftShoulder, rightShoulder, whitePaint)
        drawLine(p0, leftHip, rightHip, whitePaint)

        // Left body
        drawLine(p0, leftShoulder, leftElbow, leftPaint)
        drawLine(p0, leftElbow, leftWrist, leftPaint)
        drawLine(p0, leftShoulder, leftHip, leftPaint)
        drawLine(p0, leftHip, leftKnee, leftPaint)
        drawLine(p0, leftKnee, leftAnkle, leftPaint)
        drawLine(p0, leftWrist, leftThumb, leftPaint)
        drawLine(p0, leftWrist, leftPinky, leftPaint)
        drawLine(p0, leftWrist, leftIndex, leftPaint)
        drawLine(p0, leftIndex, leftPinky, leftPaint)
        drawLine(p0, leftAnkle, leftHeel, leftPaint)
        drawLine(p0, leftHeel, leftFootIndex, leftPaint)

        // Right body
        drawLine(p0, rightShoulder, rightElbow, rightPaint)
        drawLine(p0, rightElbow, rightWrist, rightPaint)
        drawLine(p0, rightShoulder, rightHip, rightPaint)
        drawLine(p0, rightHip, rightKnee, rightPaint)
        drawLine(p0, rightKnee, rightAnkle, rightPaint)
        drawLine(p0, rightWrist, rightThumb, rightPaint)
        drawLine(p0, rightWrist, rightPinky, rightPaint)
        drawLine(p0, rightWrist, rightIndex, rightPaint)
        drawLine(p0, rightIndex, rightPinky, rightPaint)
        drawLine(p0, rightAnkle, rightHeel, rightPaint)
        drawLine(p0, rightHeel, rightFootIndex, rightPaint)

        // Draw inFrameLikelihood for all points
        if (showInFrameLikelihood) {
            for (landmark in landmarks) {
                p0.drawText(
                    String.format(Locale.US, "%.2f", landmark.inFrameLikelihood),
                    landmark.position.x,
                    landmark.position.y,
                    whitePaint
                )
            }
        }
    }

    override fun setAlpha(p0: Int) {
        TODO("Not yet implemented")
    }

    override fun setColorFilter(p0: ColorFilter?) {
        TODO("Not yet implemented")
    }

    override fun getOpacity(): Int {
        TODO("Not yet implemented")
    }

    private fun drawPoint(canvas: Canvas, landmark: PoseLandmark, paint: Paint) {
        val point = landmark.position3D
        canvas.drawCircle(point.x, point.y, DOT_RADIUS, paint)
    }

    private fun drawLine(
        canvas: Canvas,
        startLandmark: PoseLandmark?,
        endLandmark: PoseLandmark?,
        paint: Paint
    ) {
        val start = startLandmark!!.position3D
        val end = endLandmark!!.position3D

        canvas.drawLine(
            start.x,
            start.y,
            end.x,
            end.y,
            paint
        )
    }


    companion object {
        private const val DOT_RADIUS = 8.0f
        private const val IN_FRAME_LIKELIHOOD_TEXT_SIZE = 30.0f
        private const val STROKE_WIDTH = 10.0f
    }
}
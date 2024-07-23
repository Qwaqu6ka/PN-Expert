package ru.fefu.video_tests_impl.domain

import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import ru.fefu.camera.VisionImageProcessor
import ru.fefu.camera.presentation.GraphicOverlay

interface CameraXRepository {

    fun bindPreview(previewView: PreviewView, lifecycleOwner: LifecycleOwner)

    fun setGraphicOverlay(graphicOverlay: GraphicOverlay)

    fun changeCameraFacing()

    fun clearCamera()

    fun clearAnalyzer()

    fun addPoseAnalyzer(imageProcessor: VisionImageProcessor)
}
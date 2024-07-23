package ru.fefu.pnexpert.glue.video_tests

import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import ru.fefu.camera.VisionImageProcessor
import ru.fefu.camera.presentation.GraphicOverlay
import ru.fefu.data.CameraXDataRepository
import ru.fefu.video_tests_impl.domain.CameraXRepository
import javax.inject.Inject

class AdapterCameraXRepository @Inject constructor(
    private val cameraXDataRepository: CameraXDataRepository
) : CameraXRepository {
    override fun bindPreview(previewView: PreviewView, lifecycleOwner: LifecycleOwner) =
        cameraXDataRepository.bindPreview(previewView, lifecycleOwner)

    override fun setGraphicOverlay(graphicOverlay: GraphicOverlay) =
        cameraXDataRepository.setGraphicOverlay(graphicOverlay)

    override fun changeCameraFacing() = cameraXDataRepository.changeCameraFacing()

    override fun clearCamera() = cameraXDataRepository.clearCamera()

    override fun clearAnalyzer() = cameraXDataRepository.clearAnalyzer()

    override fun addPoseAnalyzer(imageProcessor: VisionImageProcessor) =
        cameraXDataRepository.addPoseAnalyzer(imageProcessor)
}
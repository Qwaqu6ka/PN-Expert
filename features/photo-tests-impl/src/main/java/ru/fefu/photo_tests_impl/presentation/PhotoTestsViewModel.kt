package ru.fefu.photo_tests_impl.presentation

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.annotation.StringRes
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Immutable
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.google.mlkit.vision.common.InputImage
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.fefu.BaseViewModel
import ru.fefu.photo_test_impl.R
import ru.fefu.photo_tests_impl.domain.models.PhotoTestModel
import ru.fefu.photo_tests_impl.domain.models.PhotoTestTask
import ru.fefu.photo_tests_impl.domain.models.PhotoTestType
import ru.fefu.photo_tests_impl.domain.repositories.CameraRepository
import ru.fefu.photo_tests_impl.domain.repositories.PhotoTestsRepository
import ru.fefu.photo_tests_impl.navigation.ARG_PHOTO_TEST_TYPE

private const val SUCCESS_BARCODE_VALUE = "testValue"

internal class PhotoTestsViewModel(
    private val photoTestsRepository: PhotoTestsRepository,
    private val cameraRepository: CameraRepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val testType: PhotoTestType =
        PhotoTestType.valueOf(checkNotNull(savedStateHandle[ARG_PHOTO_TEST_TYPE]) { "Test type can not be null" })
    val test = photoTestsRepository.getPhotoTest(testType)

    // todo удаление фото
    /*
     *   [TaskScreen] flows
     */
    private val activeTaskIndex = MutableStateFlow(0)
    private val answers = photoTestsRepository.getAnswers()
    val taskScreenState = combineState(
        activeTaskIndex,
        answers,
        scope = viewModelScope,
        transform = ::mergeTaskScreenSources
    )
    private val _taskScreenSideEffectFlow = MutableSharedFlow<TaskScreenSideEffect>()
    val taskScreenSideEffectFlow: SharedFlow<TaskScreenSideEffect> = _taskScreenSideEffectFlow

    /*
     *   [CameraScreen] flows
     */
    private val isBarcodeDetected = MutableStateFlow(false)
    private val tempPhotoUri = MutableStateFlow(Uri.EMPTY)
    val cameraScreenState = combineState(
        isBarcodeDetected,
        tempPhotoUri,
        scope = viewModelScope,
        transform = ::mergeCameraScreenSources
    )

    /*
     *   [PhotoResultScreen] flows
     */
    private val _photoResultScreenSideEffectFlow = MutableSharedFlow<PhotoResultSideEffect>()
    val photoResultScreenSideEffectFlow: SharedFlow<PhotoResultSideEffect> =
        _photoResultScreenSideEffectFlow

    /*
     *   [TestResultScreen] flows
     */
    val testResultScreenState: StateFlow<TestResultState> = answers.map { answers ->
        TestResultState(tasks = test.testTasks, answers = answers)
    }.stateIn(viewModelScope, SharingStarted.Eagerly, TestResultState())

    fun onNextTaskClick() = viewModelScope.launch {
        if (activeTaskIndex.value < test.testTasks.lastIndex) {
            ++activeTaskIndex.value
        } else {
            _taskScreenSideEffectFlow.emit(TaskScreenSideEffect.NavigateToTestResult)
        }
    }

    fun onBackPressedTaskScreen() = viewModelScope.launch {
        if (activeTaskIndex.value > 0) {
            --activeTaskIndex.value
        } else {
            _taskScreenSideEffectFlow.emit(TaskScreenSideEffect.NavigateToGuideScreen)
        }
    }

    fun setPhotoFromFiles(photoPath: Uri, context: Context) {
        viewModelScope.launch {
            if (isFilesPhotoCorrect(photoPath, context)) {
                photoTestsRepository.setAnswer(activeTaskIndex.value, photoPath)
            } else {
                _taskScreenSideEffectFlow.emit(TaskScreenSideEffect.ShowToastRes(R.string.error_file_load_bad_qr))
            }
        }
    }

    fun captureAndSave(context: Context) {
        viewModelScope.launch {
            cameraRepository.captureAndSaveImage(context, tempPhotoUri)
        }
    }

    fun showCameraPreview(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner
    ) {
        viewModelScope.launch {
            cameraRepository.showCameraPreview(
                previewView = previewView,
                lifecycleOwner = lifecycleOwner,
                onBarcodeScanner = true,
            ) { barcodes ->
                isBarcodeDetected.value =
                    barcodes.map { it.rawValue }.contains(SUCCESS_BARCODE_VALUE)
            }
        }
    }

    fun onConfirmPhotoResultScreen() = viewModelScope.launch {
        photoTestsRepository.setAnswer(activeTaskIndex.value, tempPhotoUri.value)
        tempPhotoUri.value = Uri.EMPTY
        _photoResultScreenSideEffectFlow.emit(PhotoResultSideEffect.NavigateToTaskScreen)
    }

    fun completeTest() {
        // todo: complete test
        Log.d("debug", "submit to server")
    }

    private suspend fun isFilesPhotoCorrect(uri: Uri, context: Context): Boolean {
        val image = InputImage.fromFilePath(context, uri)
        val barcodes = cameraRepository.getImageBarcodes(image)
        return barcodes?.map { it.rawValue }?.contains(SUCCESS_BARCODE_VALUE) ?: false
    }

    private fun mergeTaskScreenSources(activeTaskIndex: Int, answers: List<Uri>) =
        TaskScreenState(test = test, activeTaskIndex = activeTaskIndex, answers = answers)

    private fun mergeCameraScreenSources(isBarcodeDetected: Boolean, tempPhotoUri: Uri) =
        CameraScreenState(tempPhotoUri = tempPhotoUri, isPhotoButtonEnabled = isBarcodeDetected)

    @Immutable
    data class TaskScreenState(
        private val test: PhotoTestModel,
        private val activeTaskIndex: Int,
        val answers: List<Uri>
    ) {
        val activeTask: PhotoTestTask get() = test.testTasks[activeTaskIndex]
        val activeTaskAnswer: Uri
            get() = try {
                answers[activeTaskIndex]
            } catch (_: Exception) {
                Uri.EMPTY
            }
        val isNextTaskButtonActive: Boolean get() = activeTaskAnswer != Uri.EMPTY
    }

    @Immutable
    data class CameraScreenState(
        val tempPhotoUri: Uri,
        val isPhotoButtonEnabled: Boolean,
    )

    @Immutable
    data class TestResultState(
        val tasks: List<PhotoTestTask> = emptyList(),
        val answers: List<Uri> = emptyList(),
    )

    @Immutable
    sealed interface TaskScreenSideEffect {
        data object NavigateToTestResult : TaskScreenSideEffect
        data object NavigateToGuideScreen : TaskScreenSideEffect
        data class ShowToastRes(@StringRes val resId: Int) : TaskScreenSideEffect
    }

    @Immutable
    sealed interface PhotoResultSideEffect {
        data object NavigateToTaskScreen : PhotoResultSideEffect
    }
}
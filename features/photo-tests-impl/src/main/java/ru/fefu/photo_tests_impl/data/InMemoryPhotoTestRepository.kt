package ru.fefu.photo_tests_impl.data

import android.net.Uri
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.fefu.photo_test_impl.R
import ru.fefu.photo_tests_impl.domain.models.PhotoTestModel
import ru.fefu.photo_tests_impl.domain.models.PhotoTestTask
import ru.fefu.photo_tests_impl.domain.models.PhotoTestType
import ru.fefu.photo_tests_impl.domain.repositories.PhotoTestsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class InMemoryPhotoTestRepository @Inject constructor() : PhotoTestsRepository {

    private val answers = MutableStateFlow(emptyList<Uri>())

    override fun getPhotoTest(photoTestType: PhotoTestType) = when (photoTestType) {
        PhotoTestType.Clock -> clockPhotoTest
        PhotoTestType.Face -> clockPhotoTest
        PhotoTestType.FullLength -> clockPhotoTest
        PhotoTestType.Handwriting -> clockPhotoTest
    }

    override fun getAnswers(): StateFlow<List<Uri>> = answers

    override fun setAnswer(answerIndex: Int, photo: Uri) {
        val newList = answers.value.toMutableList()
        newList.add(answerIndex, photo)
        answers.value = newList
    }

    private val clockPhotoTest: PhotoTestModel = PhotoTestModel(
        testTitle = R.string.clock_title,
        testGuide = R.string.clock_guide,
        testTasks = listOf(
            PhotoTestTask(
                taskDescriprion = R.string.clock_task_1,
                taskPhotoExample = R.drawable.baseline_camera_24
            ),
            PhotoTestTask(
                taskDescriprion = R.string.clock_task_2,
                taskPhotoExample = R.drawable.photo_test_clock
            )
        )
    )
}
package ru.fefu.pnexpert.glue.written_tests.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.fefu.data.WrittenTestDataRepository
import ru.fefu.pnexpert.glue.written_tests.toWrittenAnswerData
import ru.fefu.pnexpert.glue.written_tests.toWrittenAnswerFeature
import ru.fefu.written_test_impl.domain.WrittenTestRepository
import ru.fefu.written_test_impl.presentation.entities.WrittenAnswer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AdapterWrittenTestRepository @Inject constructor(
    private val writtenTestDataRepository: WrittenTestDataRepository
) : WrittenTestRepository {

    override suspend fun isTestUncompleted(testTitle: String): Boolean {
        return writtenTestDataRepository.isTestUncompleted(testTitle)
    }

    override suspend fun saveTestResult(answer: WrittenAnswer) {
        writtenTestDataRepository.saveTestResult(answer.toWrittenAnswerData())
    }

    override fun getTestResults(testTitle: String): Flow<List<WrittenAnswer>> {
        return writtenTestDataRepository.getTestResults(testTitle).map { list ->
            list.map { answer ->
                answer.toWrittenAnswerFeature()
            }
        }
    }

    override suspend fun submitResult(testTitle: String) {
        writtenTestDataRepository.submitResult(testTitle)
    }

    override suspend fun clearTest(testTitle: String) {
        writtenTestDataRepository.clearTest(testTitle)
    }

    override fun getLastAnsweredQuestion(testTitle: String): Flow<Int> =
        writtenTestDataRepository.getLastAnsweredQuestion(testTitle)

    override suspend fun setLastAnsweredQuestion(testTitle: String, lastQuestion: Int) {
        writtenTestDataRepository.setLastAnsweredQuestion(testTitle, lastQuestion)
    }
}
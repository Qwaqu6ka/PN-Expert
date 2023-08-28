package ru.fefu.pnexpert.glue.written_tests.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.fefu.data.WrittenTestDataRepository
import ru.fefu.pnexpert.glue.written_tests.toWrittenAnswerData
import ru.fefu.pnexpert.glue.written_tests.toWrittenAnswerFeature
import ru.fefu.written_test_impl.domain.repositories.WrittenTestRepository
import ru.fefu.written_test_impl.entities.testentities.WrittenAnswer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AdapterWrittenTestRepository @Inject constructor(
    private val writtenTestDataRepository: WrittenTestDataRepository
) : WrittenTestRepository {

    override suspend fun isTestCompleted(testTitle: String, testSize: Int): Boolean {
        return writtenTestDataRepository.isTestCompleted(testTitle, testSize)
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
}
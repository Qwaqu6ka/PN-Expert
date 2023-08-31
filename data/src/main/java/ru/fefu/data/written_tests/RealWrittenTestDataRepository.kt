package ru.fefu.data.written_tests

import android.util.Log
import kotlinx.coroutines.flow.Flow
import ru.fefu.data.WrittenTestDataRepository
import ru.fefu.data.written_tests.entities.WrittenAnswerData
import ru.fefu.data.written_tests.sources.WrittenTestDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RealWrittenTestDataRepository @Inject constructor(
    private val writtenTestDataSource: WrittenTestDataSource
) : WrittenTestDataRepository {

    override suspend fun isTestUncompleted(testTitle: String): Boolean {
        return writtenTestDataSource.isTestUncompleted(testTitle)
    }

    override suspend fun saveTestResult(answer: WrittenAnswerData) {
        writtenTestDataSource.saveTestResult(answer)
    }

    override fun getTestResults(testTitle: String): Flow<List<WrittenAnswerData>> {
        return writtenTestDataSource.getTestResults(testTitle)
    }

    override fun getLastAnsweredQuestion(testTitle: String): Flow<Int> =
        writtenTestDataSource.getLastAnsweredQuestion(testTitle)

    override suspend fun setLastAnsweredQuestion(testTitle: String, lastQuestion: Int) {
        writtenTestDataSource.setLastAnsweredQuestion(testTitle, lastQuestion)
    }

    override suspend fun submitResult(testTitle: String) {
        Log.d("debug", "result submit to server")
        writtenTestDataSource.clearResult(testTitle)
    }

    override suspend fun clearTest(testTitle: String) {
        writtenTestDataSource.clearResult(testTitle)
        writtenTestDataSource.setLastAnsweredQuestion(testTitle, 0)
    }
}
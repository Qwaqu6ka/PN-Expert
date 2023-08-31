package ru.fefu.data.written_tests.sources

import kotlinx.coroutines.flow.Flow
import ru.fefu.data.written_tests.entities.WrittenAnswerData

interface WrittenTestDataSource {

    suspend fun isTestUncompleted(testTitle: String): Boolean

    suspend fun saveTestResult(vararg answer: WrittenAnswerData)

    fun getTestResults(testTitle: String): Flow<List<WrittenAnswerData>>

    suspend fun clearResult(testTitle: String)

    fun getLastAnsweredQuestion(testTitle: String): Flow<Int>

    suspend fun setLastAnsweredQuestion(testTitle: String, lastQuestion: Int)
}
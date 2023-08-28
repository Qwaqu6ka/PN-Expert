package ru.fefu.data.written_tests.sources

import kotlinx.coroutines.flow.Flow
import ru.fefu.data.written_tests.entities.WrittenAnswerData

interface WrittenTestDataSource {

    suspend fun isTestCompleted(testTitle: String, testSize: Int): Boolean

    suspend fun saveTestResult(vararg answer: WrittenAnswerData)

    fun getTestResults(testTitle: String): Flow<List<WrittenAnswerData>>

    suspend fun clearResult(testTitle: String)

    suspend fun getLastAnsweredQuestion(testTitle: String): Int

    fun serLastAnsweredQuestion(testTitle: String, lastQuestion: Int)
}
package ru.fefu.data

import kotlinx.coroutines.flow.Flow
import ru.fefu.data.written_tests.entities.WrittenAnswerData

interface WrittenTestDataRepository {

    suspend fun isTestCompleted(testTitle: String, testSize: Int): Boolean

    suspend fun saveTestResult(answer: WrittenAnswerData)

    fun getTestResults(testTitle: String): Flow<List<WrittenAnswerData>>

    suspend fun getLastAnsweredQuestion(testTitle: String): Int

    suspend fun setLastAnsweredQuestion(testTitle: String, lastQuestion: Int)

    suspend fun submitResult(testTitle: String)

    suspend fun clearTest(testTitle: String)
}
package ru.fefu.written_test_impl.domain

import kotlinx.coroutines.flow.Flow
import ru.fefu.written_test_impl.entities.testentities.WrittenAnswer

interface WrittenTestRepository {

    suspend fun isTestUncompleted(testTitle: String): Boolean

    suspend fun saveTestResult(answer: WrittenAnswer)

    fun getTestResults(testTitle: String): Flow<List<WrittenAnswer>>

    suspend fun submitResult(testTitle: String)

    suspend fun clearTest(testTitle: String)

    fun getLastAnsweredQuestion(testTitle: String): Flow<Int>

    suspend fun setLastAnsweredQuestion(testTitle: String, lastQuestion: Int)
}
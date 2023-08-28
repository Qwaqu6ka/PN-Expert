package ru.fefu.written_test_impl.domain.repositories

import kotlinx.coroutines.flow.Flow
import ru.fefu.written_test_impl.entities.testentities.WrittenAnswer

interface WrittenTestRepository {

    suspend fun isTestCompleted(testTitle: String, testSize: Int): Boolean

    suspend fun saveTestResult(answer: WrittenAnswer)

    fun getTestResults(testTitle: String): Flow<List<WrittenAnswer>>

    suspend fun submitResult(testTitle: String)

    suspend fun clearTest(testTitle: String)
}
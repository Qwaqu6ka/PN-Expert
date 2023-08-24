package ru.fefu.written_test_impl.domain.repositories

import ru.fefu.written_test_impl.entities.TestType

interface WrittenTestRepository {

    suspend fun isTestCompleted(testType: TestType): Boolean

    suspend fun saveTestResult(testType: TestType, testResult: List<String?>)

    suspend fun getTestResults(testType: TestType): List<String?>

    suspend fun submitResult(testType: TestType, testResult: List<String>)

    suspend fun clearTest(testType: TestType)
}
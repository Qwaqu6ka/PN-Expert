package ru.fefu.data.written_tests.sources

interface WrittenTestDataSource {

    suspend fun isTestCompleted(testTitle: String): Boolean

    suspend fun saveTestResult(testTitle: String, testResult: List<String?>)

    suspend fun getTestResults(testTitle: String): List<String?>

    suspend fun clearResult(testTitle: String)
}
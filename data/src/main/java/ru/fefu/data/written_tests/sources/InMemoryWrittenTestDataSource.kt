package ru.fefu.data.written_tests.sources

import javax.inject.Inject

class InMemoryWrittenTestDataSource @Inject constructor() : WrittenTestDataSource {

    override suspend fun isTestCompleted(testTitle: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun saveTestResult(testTitle: String, testResult: List<String?>) {
        TODO("Not yet implemented")
    }

    override suspend fun getTestResults(testTitle: String): List<String?> {
        TODO("Not yet implemented")
    }

    override suspend fun clearResult(testTitle: String) {
        TODO("Not yet implemented")
    }
}
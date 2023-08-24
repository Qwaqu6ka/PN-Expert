package ru.fefu.written_test_impl.domain

import ru.fefu.written_test_impl.domain.repositories.WrittenTestRepository
import ru.fefu.written_test_impl.entities.TestType
import javax.inject.Inject

class HaveOldTestUseCase @Inject constructor(private val repository: WrittenTestRepository) {

    suspend fun isTestCompleted(testType: TestType): Boolean = repository.isTestCompleted(testType)

    suspend fun getOldTestResult(testType: TestType): List<String?> =
        repository.getTestResults(testType)

    suspend fun startNewTest(testType: TestType) = repository.clearTest(testType)
}
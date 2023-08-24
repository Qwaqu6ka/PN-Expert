package ru.fefu.written_test_impl.domain

import ru.fefu.written_test_impl.domain.repositories.WrittenTestRepository
import ru.fefu.written_test_impl.entities.TestType
import javax.inject.Inject

class CompleteTestUseCase @Inject constructor(private val repository: WrittenTestRepository) {

    suspend fun submitResult(testType: TestType, testResult: List<String?>) {
        if (!testResult.contains(null)) throw IllegalArgumentException("testResult should not contain null")
        repository.submitResult(testType, testResult as List<String>)
        repository.clearTest(testType)
    }
}
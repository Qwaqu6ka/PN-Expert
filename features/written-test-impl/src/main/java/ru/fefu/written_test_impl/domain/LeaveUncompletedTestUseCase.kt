package ru.fefu.written_test_impl.domain

import ru.fefu.written_test_impl.domain.repositories.WrittenTestRepository
import ru.fefu.written_test_impl.entities.TestType
import javax.inject.Inject

class LeaveUncompletedTestUseCase @Inject constructor(private val repository: WrittenTestRepository) {

    suspend fun saveResult(testType: TestType, testResult: List<String?>) =
        repository.saveTestResult(testType, testResult)
}
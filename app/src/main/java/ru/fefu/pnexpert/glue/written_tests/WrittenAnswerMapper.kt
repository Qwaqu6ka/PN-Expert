package ru.fefu.pnexpert.glue.written_tests

import ru.fefu.data.written_tests.entities.WrittenAnswerData
import ru.fefu.written_test_impl.presentation.entities.WrittenAnswer

fun WrittenAnswer.toWrittenAnswerData(): WrittenAnswerData {
    return WrittenAnswerData(
        testTitle = this.testTitle,
        questionNumber = this.questionNumber,
        value = this.value
    )
}

fun WrittenAnswerData.toWrittenAnswerFeature(): WrittenAnswer {
    return WrittenAnswer(
        testTitle = this.testTitle,
        questionNumber = this.questionNumber,
        value = this.value
    )
}
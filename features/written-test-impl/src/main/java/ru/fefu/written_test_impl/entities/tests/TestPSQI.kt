package ru.fefu.written_test_impl.entities.tests

import ru.fefu.written_test_impl.R
import ru.fefu.written_test_impl.entities.testentities.ChoiceQuestion
import ru.fefu.written_test_impl.entities.testentities.InputQuestion
import ru.fefu.written_test_impl.entities.testentities.SelectableAnswer
import ru.fefu.written_test_impl.entities.testentities.TimeQuestion
import ru.fefu.written_test_impl.entities.testentities.WrittenTest

internal object TestPSQI : WrittenTest {
    override val title = R.string.PSQI_title
    override val codeTitle = R.string.PSQI_code_title
    override val questions = listOf(
        TimeQuestion(
            text = R.string.PSQI_question1
        ),
        InputQuestion(
            text = R.string.PSQI_question2,
            hint = R.string.enter_minutes,
            validator = { it.toInt() > 0 }
        ),
        ChoiceQuestion(
            text = R.string.PSQI_question5,
            answers = listOf(
                SelectableAnswer(
                    text = R.string.PSQI_answer_1_0,
                    points = 0
                ),
                SelectableAnswer(
                    text = R.string.PSQI_answer_1_1,
                    points = 1
                ),
                SelectableAnswer(
                    text = R.string.PSQI_answer_1_2,
                    points = 2
                ),
                SelectableAnswer(
                    text = R.string.PSQI_answer_1_3,
                    points = 3
                )
            )
        )
    )
}
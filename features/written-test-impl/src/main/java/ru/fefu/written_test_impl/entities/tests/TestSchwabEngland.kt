package ru.fefu.written_test_impl.entities.tests

import ru.fefu.written_test_impl.R
import ru.fefu.written_test_impl.entities.testentities.ChoiceQuestion
import ru.fefu.written_test_impl.entities.testentities.SelectableAnswer
import ru.fefu.written_test_impl.entities.testentities.WrittenTest

internal object TestSchwabEngland : WrittenTest {
    override val title = R.string.SchwabEngland_title
    override val codeTitle = R.string.SchwabEngland_code_title
    override val questions = listOf(
        ChoiceQuestion(
            text = R.string.SchwabEngland_question1,
            answers = listOf(
                SelectableAnswer(
                    text = R.string.SchwabEngland_answer_0,
                    points = 0
                ),
                SelectableAnswer(
                    text = R.string.SchwabEngland_answer_1,
                    points = 1
                ),
                SelectableAnswer(
                    text = R.string.SchwabEngland_answer_2,
                    points = 2
                ),
                SelectableAnswer(
                    text = R.string.SchwabEngland_answer_3,
                    points = 3
                ),
                SelectableAnswer(
                    text = R.string.SchwabEngland_answer_4,
                    points = 4
                ),
                SelectableAnswer(
                    text = R.string.SchwabEngland_answer_5,
                    points = 5
                ),
                SelectableAnswer(
                    text = R.string.SchwabEngland_answer_6,
                    points = 6
                ),
                SelectableAnswer(
                    text = R.string.SchwabEngland_answer_7,
                    points = 7
                ),
                SelectableAnswer(
                    text = R.string.SchwabEngland_answer_8,
                    points = 8
                ),
                SelectableAnswer(
                    text = R.string.SchwabEngland_answer_9,
                    points = 9
                ),
                SelectableAnswer(
                    text = R.string.SchwabEngland_answer_10,
                    points = 10
                )
            )
        )
    )
}
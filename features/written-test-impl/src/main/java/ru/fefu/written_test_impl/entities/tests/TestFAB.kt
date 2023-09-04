package ru.fefu.written_test_impl.entities.tests

import ru.fefu.written_test_impl.R
import ru.fefu.written_test_impl.entities.testentities.ChoiceQuestion
import ru.fefu.written_test_impl.entities.testentities.SelectableAnswer
import ru.fefu.written_test_impl.entities.testentities.WrittenTest

internal object TestFAB : WrittenTest {
    override val title = R.string.FAB_title
    override val codeTitle = R.string.FAB_code_title
    override val questions = listOf(
        ChoiceQuestion(
            text = R.string.FAB_question1,
            answers = listOf(
                SelectableAnswer(
                    text = R.string.FAB_answer_0,
                    points = 0
                ),
                SelectableAnswer(
                    text = R.string.FAB_answer_1,
                    points = 1
                ),
                SelectableAnswer(
                    text = R.string.FAB_answer_2,
                    points = 2
                ),
                SelectableAnswer(
                    text = R.string.FAB_answer_3,
                    points = 3
                )
            )
        ),
        ChoiceQuestion(
            text = R.string.FAB_question2,
            answers = listOf(
                SelectableAnswer(
                    text = R.string.FAB_answer_0,
                    points = 0
                ),
                SelectableAnswer(
                    text = R.string.FAB_answer_1,
                    points = 1
                ),
                SelectableAnswer(
                    text = R.string.FAB_answer_2,
                    points = 2
                ),
                SelectableAnswer(
                    text = R.string.FAB_answer_3,
                    points = 3
                )
            )
        ),
        ChoiceQuestion(
            text = R.string.FAB_question3,
            answers = listOf(
                SelectableAnswer(
                    text = R.string.FAB_answer_0,
                    points = 0
                ),
                SelectableAnswer(
                    text = R.string.FAB_answer_1,
                    points = 1
                ),
                SelectableAnswer(
                    text = R.string.FAB_answer_2,
                    points = 2
                ),
                SelectableAnswer(
                    text = R.string.FAB_answer_3,
                    points = 3
                )
            )
        ),
        ChoiceQuestion(
            text = R.string.FAB_question4,
            answers = listOf(
                SelectableAnswer(
                    text = R.string.FAB_answer_0,
                    points = 0
                ),
                SelectableAnswer(
                    text = R.string.FAB_answer_1,
                    points = 1
                ),
                SelectableAnswer(
                    text = R.string.FAB_answer_2,
                    points = 2
                ),
                SelectableAnswer(
                    text = R.string.FAB_answer_3,
                    points = 3
                )
            )
        ),
        ChoiceQuestion(
            text = R.string.FAB_question5,
            answers = listOf(
                SelectableAnswer(
                    text = R.string.FAB_answer_0,
                    points = 0
                ),
                SelectableAnswer(
                    text = R.string.FAB_answer_1,
                    points = 1
                ),
                SelectableAnswer(
                    text = R.string.FAB_answer_2,
                    points = 2
                ),
                SelectableAnswer(
                    text = R.string.FAB_answer_3,
                    points = 3
                )
            )
        ),
        ChoiceQuestion(
            text = R.string.FAB_question6,
            answers = listOf(
                SelectableAnswer(
                    text = R.string.FAB_answer_0,
                    points = 0
                ),
                SelectableAnswer(
                    text = R.string.FAB_answer_1,
                    points = 1
                ),
                SelectableAnswer(
                    text = R.string.FAB_answer_2,
                    points = 2
                ),
                SelectableAnswer(
                    text = R.string.FAB_answer_3,
                    points = 3
                )
            )
        )
    )
}

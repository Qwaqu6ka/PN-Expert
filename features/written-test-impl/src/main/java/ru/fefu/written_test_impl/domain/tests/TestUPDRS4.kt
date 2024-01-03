package ru.fefu.written_test_impl.domain.tests

import ru.fefu.written_test_impl.R
import ru.fefu.written_test_impl.presentation.entities.ChoiceQuestion
import ru.fefu.written_test_impl.presentation.entities.SelectableAnswer
import ru.fefu.written_test_impl.presentation.entities.WrittenTest

internal object TestUPDRS4 : WrittenTest {
    override val title = R.string.UPDRS4_title
    override val codeTitle = R.string.UPDRS4_code_title
    override val questions = listOf(
        ChoiceQuestion(
            text = R.string.UPDRS4_question1,
            answers = listOf(
                SelectableAnswer(
                    text = R.string.UPDRS4_answer_1_0,
                    points = 0
                ),
                SelectableAnswer(
                    text = R.string.UPDRS4_answer_1_1,
                    points = 1
                ),
                SelectableAnswer(
                    text = R.string.UPDRS4_answer_1_2,
                    points = 2
                ),
                SelectableAnswer(
                    text = R.string.UPDRS4_answer_1_3,
                    points = 3
                ),
                SelectableAnswer(
                    text = R.string.UPDRS4_answer_1_4,
                    points = 4
                )
            )
        ),
        ChoiceQuestion(
            text = R.string.UPDRS4_question2,
            answers = listOf(
                SelectableAnswer(
                    text = R.string.UPDRS4_answer_2_0,
                    points = 0
                ),
                SelectableAnswer(
                    text = R.string.UPDRS4_answer_2_1,
                    points = 1
                ),
                SelectableAnswer(
                    text = R.string.UPDRS4_answer_2_2,
                    points = 2
                ),
                SelectableAnswer(
                    text = R.string.UPDRS4_answer_2_3,
                    points = 3
                ),
                SelectableAnswer(
                    text = R.string.UPDRS4_answer_2_4,
                    points = 4
                )
            )
        ),
        ChoiceQuestion(
            text = R.string.UPDRS4_question3,
            answers = listOf(
                SelectableAnswer(
                    text = R.string.UPDRS4_answer_3_0,
                    points = 0
                ),
                SelectableAnswer(
                    text = R.string.UPDRS4_answer_3_1,
                    points = 1
                ),
                SelectableAnswer(
                    text = R.string.UPDRS4_answer_3_2,
                    points = 2
                ),
                SelectableAnswer(
                    text = R.string.UPDRS4_answer_3_3,
                    points = 3
                ),
                SelectableAnswer(
                    text = R.string.UPDRS4_answer_3_4,
                    points = 4
                )
            )
        ),
        ChoiceQuestion(
            text = R.string.UPDRS4_question4,
            answers = listOf(
                SelectableAnswer(
                    text = R.string.UPDRS4_answer_4_0,
                    points = 0
                ),
                SelectableAnswer(
                    text = R.string.UPDRS4_answer_4_1,
                    points = 1
                ),
                SelectableAnswer(
                    text = R.string.UPDRS4_answer_4_2,
                    points = 2
                ),
                SelectableAnswer(
                    text = R.string.UPDRS4_answer_4_3,
                    points = 3
                ),
                SelectableAnswer(
                    text = R.string.UPDRS4_answer_4_4,
                    points = 4
                )
            )
        ),
        ChoiceQuestion(
            text = R.string.UPDRS4_question5,
            answers = listOf(
                SelectableAnswer(
                    text = R.string.UPDRS4_answer_5_0,
                    points = 0
                ),
                SelectableAnswer(
                    text = R.string.UPDRS4_answer_5_1,
                    points = 1
                ),
                SelectableAnswer(
                    text = R.string.UPDRS4_answer_5_2,
                    points = 2
                ),
                SelectableAnswer(
                    text = R.string.UPDRS4_answer_5_3,
                    points = 3
                ),
                SelectableAnswer(
                    text = R.string.UPDRS4_answer_5_4,
                    points = 4
                )
            )
        ),
        ChoiceQuestion(
            text = R.string.UPDRS4_question6,
            answers = listOf(
                SelectableAnswer(
                    text = R.string.UPDRS4_answer_6_0,
                    points = 0
                ),
                SelectableAnswer(
                    text = R.string.UPDRS4_answer_6_1,
                    points = 1
                ),
                SelectableAnswer(
                    text = R.string.UPDRS4_answer_6_2,
                    points = 2
                ),
                SelectableAnswer(
                    text = R.string.UPDRS4_answer_6_3,
                    points = 3
                ),
                SelectableAnswer(
                    text = R.string.UPDRS4_answer_6_4,
                    points = 4
                )
            )
        )
    )

}
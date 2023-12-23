package ru.fefu.written_test_impl.entities.tests

import ru.fefu.written_test_impl.R
import ru.fefu.written_test_impl.entities.testentities.ChoiceQuestion
import ru.fefu.written_test_impl.entities.testentities.SelectableAnswer
import ru.fefu.written_test_impl.entities.testentities.WrittenTest

internal object TestHADS : WrittenTest {
    override val title = R.string.HADS_title
    override val codeTitle = R.string.HADS_code_title
    override val questions = listOf(
        ChoiceQuestion(
            text = R.string.HADS_question1,
            answers = listOf(
                SelectableAnswer(
                    text = R.string.HADS_answer_dont_feel,
                    points = 0
                ),
                SelectableAnswer(
                    text = R.string.HADS_answer_1_1,
                    points = 1
                ),
                SelectableAnswer(
                    text = R.string.HADS_answer_often,
                    points = 2
                ),
                SelectableAnswer(
                    text = R.string.HADS_answer_all_time,
                    points = 3
                )
            )
        ),
        ChoiceQuestion(
            text = R.string.HADS_question2,
            answers = listOf(
                SelectableAnswer(
                    text = R.string.HADS_answer_dont_feel,
                    points = 0
                ),
                SelectableAnswer(
                    text = R.string.HADS_answer_2_1,
                    points = 1
                ),
                SelectableAnswer(
                    text = R.string.HADS_answer_2_2,
                    points = 2
                ),
                SelectableAnswer(
                    text = R.string.HADS_answer_2_3,
                    points = 3
                )
            )
        ),
        ChoiceQuestion(
            text = R.string.HADS_question3,
            answers = listOf(
                SelectableAnswer(
                    text = R.string.HADS_answer_3_0,
                    points = 0
                ),
                SelectableAnswer(
                    text = R.string.HADS_answer_3_1,
                    points = 1
                ),
                SelectableAnswer(
                    text = R.string.HADS_answer_3_2,
                    points = 2
                ),
                SelectableAnswer(
                    text = R.string.HADS_answer_3_3,
                    points = 3
                )
            )
        ),
        ChoiceQuestion(
            text = R.string.HADS_question4,
            answers = listOf(
                SelectableAnswer(
                    text = R.string.HADS_answer_4_0,
                    points = 0
                ),
                SelectableAnswer(
                    text = R.string.HADS_answer_4_1,
                    points = 1
                ),
                SelectableAnswer(
                    text = R.string.HADS_answer_4_2,
                    points = 2
                ),
                SelectableAnswer(
                    text = R.string.HADS_answer_4_3,
                    points = 3
                )
            )
        ),
        ChoiceQuestion(
            text = R.string.HADS_question5,
            answers = listOf(
                SelectableAnswer(
                    text = R.string.HADS_answer_dont_feel,
                    points = 0
                ),
                SelectableAnswer(
                    text = R.string.HADS_answer_sometimes,
                    points = 1
                ),
                SelectableAnswer(
                    text = R.string.HADS_answer_often,
                    points = 2
                ),
                SelectableAnswer(
                    text = R.string.HADS_answer_very_often,
                    points = 3
                )
            )
        ),
        ChoiceQuestion(
            text = R.string.HADS_question6,
            answers = listOf(
                SelectableAnswer(
                    text = R.string.HADS_answer_dont_feel,
                    points = 0
                ),
                SelectableAnswer(
                    text = R.string.HADS_answer_6_1,
                    points = 1
                ),
                SelectableAnswer(
                    text = R.string.HADS_answer_4_1,
                    points = 2
                ),
                SelectableAnswer(
                    text = R.string.HADS_answer_4_0,
                    points = 3
                )
            )
        ),
        ChoiceQuestion(
            text = R.string.HADS_question7,
            answers = listOf(
                SelectableAnswer(
                    text = R.string.HADS_answer_7_0,
                    points = 0
                ),
                SelectableAnswer(
                    text = R.string.HADS_answer_7_1,
                    points = 1
                ),
                SelectableAnswer(
                    text = R.string.HADS_answer_7_2,
                    points = 2
                ),
                SelectableAnswer(
                    text = R.string.HADS_answer_very_often,
                    points = 3
                )
            )
        ),
        ChoiceQuestion(
            text = R.string.HADS_question8,
            answers = listOf(
                SelectableAnswer(
                    text = R.string.HADS_answer_4_0,
                    points = 0
                ),
                SelectableAnswer(
                    text = R.string.HADS_answer_8_1,
                    points = 1
                ),
                SelectableAnswer(
                    text = R.string.HADS_answer_8_2,
                    points = 2
                ),
                SelectableAnswer(
                    text = R.string.HADS_answer_8_3,
                    points = 3
                )
            )
        ),
        ChoiceQuestion(
            text = R.string.HADS_question9,
            answers = listOf(
                SelectableAnswer(
                    text = R.string.HADS_answer_4_0,
                    points = 0
                ),
                SelectableAnswer(
                    text = R.string.HADS_answer_8_1,
                    points = 1
                ),
                SelectableAnswer(
                    text = R.string.HADS_answer_8_2,
                    points = 2
                ),
                SelectableAnswer(
                    text = R.string.HADS_answer_9_3,
                    points = 3
                )
            )
        ),
        ChoiceQuestion(
            text = R.string.HADS_question10,
            answers = listOf(
                SelectableAnswer(
                    text = R.string.HADS_answer_10_0,
                    points = 0
                ),
                SelectableAnswer(
                    text = R.string.HADS_answer_sometimes,
                    points = 1
                ),
                SelectableAnswer(
                    text = R.string.HADS_answer_10_2,
                    points = 2
                ),
                SelectableAnswer(
                    text = R.string.HADS_answer_dont_feel,
                    points = 3
                )
            )
        ),
        ChoiceQuestion(
            text = R.string.HADS_question11,
            answers = listOf(
                SelectableAnswer(
                    text = R.string.HADS_answer_11_0,
                    points = 0
                ),
                SelectableAnswer(
                    text = R.string.HADS_answer_sometimes,
                    points = 1
                ),
                SelectableAnswer(
                    text = R.string.HADS_answer_often,
                    points = 2
                ),
                SelectableAnswer(
                    text = R.string.HADS_answer_10_0,
                    points = 3
                )
            )
        ),
        ChoiceQuestion(
            text = R.string.HADS_question12,
            answers = listOf(
                SelectableAnswer(
                    text = R.string.HADS_answer_12_0,
                    points = 0
                ),
                SelectableAnswer(
                    text = R.string.HADS_answer_12_1,
                    points = 1
                ),
                SelectableAnswer(
                    text = R.string.HADS_answer_12_2,
                    points = 2
                ),
                SelectableAnswer(
                    text = R.string.HADS_answer_4_0,
                    points = 3
                )
            )
        ),
        ChoiceQuestion(
            text = R.string.HADS_question13,
            answers = listOf(
                SelectableAnswer(
                    text = R.string.HADS_answer_13_0,
                    points = 0
                ),
                SelectableAnswer(
                    text = R.string.HADS_answer_13_1,
                    points = 1
                ),
                SelectableAnswer(
                    text = R.string.HADS_answer_13_2,
                    points = 2
                ),
                SelectableAnswer(
                    text = R.string.HADS_answer_13_3,
                    points = 3
                )
            )
        ),
        ChoiceQuestion(
            text = R.string.HADS_question14,
            answers = listOf(
                SelectableAnswer(
                    text = R.string.HADS_answer_often,
                    points = 0
                ),
                SelectableAnswer(
                    text = R.string.HADS_answer_sometimes,
                    points = 1
                ),
                SelectableAnswer(
                    text = R.string.HADS_answer_14_2,
                    points = 2
                ),
                SelectableAnswer(
                    text = R.string.HADS_answer_10_2,
                    points = 3
                )
            )
        )
    )
}
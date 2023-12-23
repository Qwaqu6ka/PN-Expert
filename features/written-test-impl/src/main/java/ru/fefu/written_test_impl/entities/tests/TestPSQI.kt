package ru.fefu.written_test_impl.entities.tests

import androidx.core.text.isDigitsOnly
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
            validator = { it != null && it.isDigitsOnly() }
        ),
        TimeQuestion(
            text = R.string.PSQI_question3
        ),
        InputQuestion(
            text = R.string.PSQI_question4,
            hint = R.string.enter_hours,
            validator = { it != null && it.isDigitsOnly() }
        ),
        ChoiceQuestion(
            text = R.string.PSQI_question5a,
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
        ),
        ChoiceQuestion(
            text = R.string.PSQI_question5b,
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
        ),
        ChoiceQuestion(
            text = R.string.PSQI_question5c,
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
        ),
        ChoiceQuestion(
            text = R.string.PSQI_question5d,
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
        ),
        ChoiceQuestion(
            text = R.string.PSQI_question5e,
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
        ),
        ChoiceQuestion(
            text = R.string.PSQI_question5f,
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
        ),
        ChoiceQuestion(
            text = R.string.PSQI_question5g,
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
        ),
        ChoiceQuestion(
            text = R.string.PSQI_question5h,
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
        ),
        ChoiceQuestion(
            text = R.string.PSQI_question5i,
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
        ),
        ChoiceQuestion(
            text = R.string.PSQI_question5j,
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
        ),
        ChoiceQuestion(
            text = R.string.PSQI_question6,
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
        ),
        ChoiceQuestion(
            text = R.string.PSQI_question7,
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
        ),
        ChoiceQuestion(
            text = R.string.PSQI_question8,
            answers = listOf(
                SelectableAnswer(
                    text = R.string.PSQI_answer_2_0,
                    points = 0
                ),
                SelectableAnswer(
                    text = R.string.PSQI_answer_2_1,
                    points = 1
                ),
                SelectableAnswer(
                    text = R.string.PSQI_answer_2_2,
                    points = 2
                ),
                SelectableAnswer(
                    text = R.string.PSQI_answer_2_3,
                    points = 3
                )
            )
        ),
        ChoiceQuestion(
            text = R.string.PSQI_question9,
            answers = listOf(
                SelectableAnswer(
                    text = R.string.PSQI_answer_3_0,
                    points = 0
                ),
                SelectableAnswer(
                    text = R.string.PSQI_answer_3_1,
                    points = 1
                ),
                SelectableAnswer(
                    text = R.string.PSQI_answer_3_2,
                    points = 2
                ),
                SelectableAnswer(
                    text = R.string.PSQI_answer_3_3,
                    points = 3
                )
            )
        ),
    )
}
package ru.fefu.written_test_impl.domain.tests

import ru.fefu.written_test_impl.R
import ru.fefu.written_test_impl.presentation.entities.ChoiceQuestion
import ru.fefu.written_test_impl.presentation.entities.SelectableAnswer
import ru.fefu.written_test_impl.presentation.entities.WrittenTest

internal object TestUPDRS3 : WrittenTest {
    override val title = R.string.UPDRS2_title
    override val codeTitle = R.string.UPDRS2_code_title
    override val questions = listOf(
        ChoiceQuestion(
            text = R.string.UPDRS2_question1,
            answers = listOf(
                SelectableAnswer(
                    text = R.string.UPDRS2_answer_1_0,
                    points = 0
                ),
                SelectableAnswer(
                    text = R.string.UPDRS2_answer_1_1,
                    points = 1
                ),
                SelectableAnswer(
                    text = R.string.UPDRS2_answer_1_2,
                    points = 2
                ),
                SelectableAnswer(
                    text = R.string.UPDRS2_answer_1_3,
                    points = 3
                ),
                SelectableAnswer(
                    text = R.string.UPDRS2_answer_1_4,
                    points = 4
                )
            )
        ),
    )
}
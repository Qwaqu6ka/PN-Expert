package ru.fefu.written_test_impl.domain.tests

import ru.fefu.written_test_impl.R
import ru.fefu.written_test_impl.presentation.entities.ChoiceQuestion
import ru.fefu.written_test_impl.presentation.entities.SelectableAnswer
import ru.fefu.written_test_impl.presentation.entities.WrittenTest

internal object TestHoehnYahr : WrittenTest {
    override val title = R.string.HoehnYahr_title
    override val codeTitle = R.string.HoehnYahr_code_title
    override val questions = listOf(
        ChoiceQuestion(
            text = R.string.HoehnYahr_question1,
            answers = listOf(
                SelectableAnswer(
                    text = R.string.HoehnYahr_answer_0,
                    points = 0
                ),
                SelectableAnswer(
                    text = R.string.HoehnYahr_answer_1,
                    points = 1
                ),
                SelectableAnswer(
                    text = R.string.HoehnYahr_answer_2,
                    points = 2
                ),
                SelectableAnswer(
                    text = R.string.HoehnYahr_answer_3,
                    points = 3
                ),
                SelectableAnswer(
                    text = R.string.HoehnYahr_answer_4,
                    points = 4
                ),
                SelectableAnswer(
                    text = R.string.HoehnYahr_answer_5,
                    points = 5
                )
            )
        )
    )
}
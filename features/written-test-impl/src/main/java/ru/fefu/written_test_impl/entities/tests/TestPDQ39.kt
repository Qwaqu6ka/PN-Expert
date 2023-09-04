package ru.fefu.written_test_impl.entities.tests

import ru.fefu.written_test_impl.R
import ru.fefu.written_test_impl.entities.testentities.ChoiceQuestion
import ru.fefu.written_test_impl.entities.testentities.SelectableAnswer
import ru.fefu.written_test_impl.entities.testentities.WrittenTest

internal object TestPDQ39 : WrittenTest {
    override val title = R.string.PDQ39_title
    override val codeTitle = R.string.PDQ39_code_title
    override val questions = (1..39).map { questionNumber ->
        val questionTextResId = when (questionNumber) {
            1 -> R.string.PDQ39_question1
            2 -> R.string.PDQ39_question2
            3 -> R.string.PDQ39_question3
            4 -> R.string.PDQ39_question4
            5 -> R.string.PDQ39_question5
            6 -> R.string.PDQ39_question6
            7 -> R.string.PDQ39_question7
            8 -> R.string.PDQ39_question8
            9 -> R.string.PDQ39_question9
            10 -> R.string.PDQ39_question10
            11 -> R.string.PDQ39_question11
            12 -> R.string.PDQ39_question12
            13 -> R.string.PDQ39_question13
            14 -> R.string.PDQ39_question14
            15 -> R.string.PDQ39_question15
            16 -> R.string.PDQ39_question16
            17 -> R.string.PDQ39_question17
            18 -> R.string.PDQ39_question18
            19 -> R.string.PDQ39_question19
            20 -> R.string.PDQ39_question20
            21 -> R.string.PDQ39_question21
            22 -> R.string.PDQ39_question22
            23 -> R.string.PDQ39_question23
            24 -> R.string.PDQ39_question24
            25 -> R.string.PDQ39_question25
            26 -> R.string.PDQ39_question26
            27 -> R.string.PDQ39_question27
            28 -> R.string.PDQ39_question28
            29 -> R.string.PDQ39_question29
            30 -> R.string.PDQ39_question30
            31 -> R.string.PDQ39_question31
            32 -> R.string.PDQ39_question32
            33 -> R.string.PDQ39_question33
            34 -> R.string.PDQ39_question34
            35 -> R.string.PDQ39_question35
            36 -> R.string.PDQ39_question36
            37 -> R.string.PDQ39_question37
            38 -> R.string.PDQ39_question38
            39 -> R.string.PDQ39_question39
            else -> throw IllegalArgumentException("Resource of this question doesn't exist")
        }

        ChoiceQuestion(
            text = questionTextResId,
            answers = listOf(
                SelectableAnswer(
                    text = R.string.PDQ39_answer_0,
                    points = 0
                ),
                SelectableAnswer(
                    text = R.string.PDQ39_answer_1,
                    points = 1
                ),
                SelectableAnswer(
                    text = R.string.PDQ39_answer_2,
                    points = 2
                ),
                SelectableAnswer(
                    text = R.string.PDQ39_answer_3,
                    points = 3
                ),
                SelectableAnswer(
                    text = R.string.HoehnYahr_answer_4,
                    points = 4
                )
            )
        )
    }
}
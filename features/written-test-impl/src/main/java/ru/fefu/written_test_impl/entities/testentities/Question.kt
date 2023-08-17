package ru.fefu.written_test_impl.entities.testentities

import androidx.annotation.StringRes

internal sealed class Question(
    @StringRes val text: Int,
    val isAnswered: Boolean = false
)

internal class ChoiceQuestion(
    @StringRes text: Int,
    val answers: List<SelectableAnswer>
) : Question(text)

internal class TimeQuestion(
    @StringRes text: Int,
    val answer: TimeAnswer? = null
) : Question(text)

internal class InputQuestion<T>(
    @StringRes text: Int,
    val answer: InputAnswer<T>
) : Question(text)
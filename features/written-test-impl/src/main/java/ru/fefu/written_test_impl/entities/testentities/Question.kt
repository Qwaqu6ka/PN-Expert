package ru.fefu.written_test_impl.entities.testentities

import androidx.annotation.StringRes

sealed class Question(
    @StringRes val text: Int
)

class ChoiceQuestion(
    @StringRes text: Int,
    val answers: List<SelectableAnswer>
) : Question(text)

class TimeQuestion(
    @StringRes text: Int
) : Question(text)

class InputQuestion(
    @StringRes text: Int,
    @StringRes val hint: Int,
    val validator: ((String) -> Boolean)? = null
) : Question(text)

data class SelectableAnswer(
    @StringRes val text: Int,
    val points: Int
)
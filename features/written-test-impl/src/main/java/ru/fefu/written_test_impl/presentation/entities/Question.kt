package ru.fefu.written_test_impl.presentation.entities

import androidx.annotation.StringRes

typealias InputQuestionValidator = (String?) -> Boolean

internal sealed class Question(
    @StringRes val text: Int
)

internal class ChoiceQuestion(
    @StringRes text: Int,
    val answers: List<SelectableAnswer>
) : Question(text)

internal class TimeQuestion(
    @StringRes text: Int
) : Question(text)

internal class InputQuestion(
    @StringRes text: Int,
    @StringRes val hint: Int,
    val validator: InputQuestionValidator? = null
) : Question(text)

internal data class SelectableAnswer(
    @StringRes val text: Int,
    val points: Int
)
package ru.fefu.written_test_impl.entities.testentities

import androidx.annotation.StringRes

internal class SelectableAnswer(
    @StringRes val text: Int,
    val points: Int
)

internal class InputAnswer<T>(
    @StringRes val hint: Int,
    var value: T? = null,
    val validator: ((T) -> Boolean)? = null
)

internal class TimeAnswer(
    var hours: Int? = null,
    var minutes: Int? = null
)
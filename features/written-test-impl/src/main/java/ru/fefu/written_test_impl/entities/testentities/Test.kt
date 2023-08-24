package ru.fefu.written_test_impl.entities.testentities

import androidx.annotation.StringRes

interface WrittenTest {
    @get:StringRes val title: Int
    @get:StringRes val codeTitle: Int
    val questions: List<Question>
}
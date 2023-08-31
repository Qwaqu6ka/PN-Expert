package ru.fefu.data.written_tests.entities

import androidx.room.Entity

@Entity(primaryKeys = ["testTitle", "questionNumber"])
data class WrittenAnswerData(
    val testTitle: String,
    val questionNumber: Int,
    val value: String
)
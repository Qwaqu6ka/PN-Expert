package ru.fefu.data.written_tests.entities

import android.content.Context
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase

@Entity(primaryKeys = ["testTitle", "questionNumber"])
data class Answer(
    val testTitle: String,
    val questionNumber: Int,
    val value: String,
    val appContext: Context,
    val db: AppDatabase = Room.databaseBuilder(
        appContext,
        AppDatabase::class.java,
        "name"
    ).build()
)

abstract class AppDatabase: RoomDatabase()
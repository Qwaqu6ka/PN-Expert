package ru.fefu.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.fefu.data.written_tests.sources.WrittenTestDao
import ru.fefu.data.written_tests.entities.WrittenAnswerData

@Database(entities = [WrittenAnswerData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun writtenTestDao(): WrittenTestDao
}
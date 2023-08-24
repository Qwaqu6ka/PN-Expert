package ru.fefu.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.fefu.data.written_tests.WrittenTestDao
import ru.fefu.data.written_tests.entities.Answer

@Database(entities = [Answer::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun writtenTestDao(): WrittenTestDao
}
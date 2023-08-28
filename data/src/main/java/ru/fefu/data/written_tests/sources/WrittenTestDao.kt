package ru.fefu.data.written_tests.sources

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import ru.fefu.data.written_tests.entities.WrittenAnswerData

@Dao
interface WrittenTestDao {

    @Query("SELECT * FROM WrittenAnswerData WHERE testTitle IN (:testTitle)")
    fun getAllInTest(testTitle: String): Flow<List<WrittenAnswerData>>

    @Delete
    suspend fun delete(vararg answers: WrittenAnswerData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAnswers(vararg answers: WrittenAnswerData)

    @Transaction
    suspend fun deleteAllInTest(testTitle: String) {
        val list = getAllInTest(testTitle).first()
        delete(*list.toTypedArray())
    }
}
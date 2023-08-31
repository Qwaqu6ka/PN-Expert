package ru.fefu.data.written_tests.sources

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import ru.fefu.data.database.AppDatabase
import ru.fefu.data.written_tests.entities.WrittenAnswerData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseWrittenTestDataSource @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val db: AppDatabase,
    private val dataStore: DataStore<Preferences>
) : WrittenTestDataSource {

    override suspend fun isTestUncompleted(testTitle: String): Boolean =
        withContext(ioDispatcher) {
            val answersDef = async {
                db.writtenTestDao().getAllInTest(testTitle).first()
            }
            answersDef.await().isNotEmpty()
        }

    override suspend fun saveTestResult(vararg answer: WrittenAnswerData) =
        withContext(ioDispatcher) {
            db.writtenTestDao().addAnswers(*answer)
        }

    override fun getTestResults(testTitle: String): Flow<List<WrittenAnswerData>> =
        db.writtenTestDao().getAllInTest(testTitle)

    override suspend fun clearResult(testTitle: String) = withContext(ioDispatcher) {
        db.writtenTestDao().deleteAllInTest(testTitle)
    }

    override fun getLastAnsweredQuestion(testTitle: String): Flow<Int> {
        val key = intPreferencesKey(testTitle)
        return dataStore.data.map { preferences ->
            preferences[key] ?: 0
        }
    }

    override suspend fun setLastAnsweredQuestion(testTitle: String, lastQuestion: Int) {
        val key = intPreferencesKey(testTitle)
        dataStore.edit { preferences ->
            preferences[key] = lastQuestion
        }
    }
}
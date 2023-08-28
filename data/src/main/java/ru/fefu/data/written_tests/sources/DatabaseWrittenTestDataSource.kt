package ru.fefu.data.written_tests.sources

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import ru.fefu.data.database.AppDatabase
import ru.fefu.data.written_tests.entities.WrittenAnswerData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseWrittenTestDataSource @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val db: AppDatabase
) : WrittenTestDataSource {

    override suspend fun isTestCompleted(testTitle: String, testSize: Int): Boolean =
        withContext(ioDispatcher) {
            val answersDef = async {
                db.writtenTestDao().getAllInTest(testTitle).first()
            }
            answersDef.await().size == testSize
        }

    override suspend fun saveTestResult(vararg answer: WrittenAnswerData) =
        withContext(ioDispatcher) {
            Log.d("debug", "Go to db")
            db.writtenTestDao().addAnswers(*answer)
        }

    override fun getTestResults(testTitle: String): Flow<List<WrittenAnswerData>> =
        db.writtenTestDao().getAllInTest(testTitle)

    override suspend fun clearResult(testTitle: String) = withContext(ioDispatcher) {
        db.writtenTestDao().deleteAllInTest(testTitle)
    }

    override suspend fun getLastAnsweredQuestion(testTitle: String): Int {
        TODO("Not yet implemented")
    }

    override fun serLastAnsweredQuestion(testTitle: String, lastQuestion: Int) {
        TODO("Not yet implemented")
    }
}
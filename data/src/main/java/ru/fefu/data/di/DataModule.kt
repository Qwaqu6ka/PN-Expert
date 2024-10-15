package ru.fefu.data.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.fefu.common.di.KOIN_IO_DISPATCHER
import ru.fefu.data.CameraXDataRepository
import ru.fefu.data.WrittenTestDataRepository
import ru.fefu.data.calendar.repositoty.CalendarEventsDataRepository
import ru.fefu.data.calendar.repositoty.CalendarEventsDataRepositoryImpl
import ru.fefu.data.database.AppDatabase
import ru.fefu.data.written_tests.RealWrittenTestDataRepository
import ru.fefu.data.written_tests.sources.DatabaseWrittenTestDataSource
import ru.fefu.data.written_tests.sources.WrittenTestDataSource

private const val DATABASE_NAME = "PnExpert_database"
private const val DATASTORE_PREFERENCES = "PnExpert_datastore_preferences"

val dataModule = module {

    singleOf(::CameraXDataRepository)

    single<AppDatabase> {
        Room.databaseBuilder(androidApplication(), AppDatabase::class.java, DATABASE_NAME).build()
    }
    single<DataStore<Preferences>> {
        PreferenceDataStoreFactory.create {
            androidApplication().preferencesDataStoreFile(DATASTORE_PREFERENCES)
        }
    }

    singleOf<CalendarEventsDataRepository>(::CalendarEventsDataRepositoryImpl)

    single<WrittenTestDataSource> {
        DatabaseWrittenTestDataSource(
            ioDispatcher = get(named(KOIN_IO_DISPATCHER)),
            db = get(),
            dataStore = get()
        )
    }
    single<WrittenTestDataRepository> {
        RealWrittenTestDataRepository(writtenTestDataSource = get())
    }
}
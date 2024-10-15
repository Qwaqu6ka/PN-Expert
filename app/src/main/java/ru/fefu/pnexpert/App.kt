package ru.fefu.pnexpert

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.fefu.calendar_impl.di.calendarModule
import ru.fefu.common.di.coroutinesModule
import ru.fefu.data.di.dataModule
import ru.fefu.main_impl.di.mainPageModule
import ru.fefu.photo_tests_impl.di.photoTestsModule
import ru.fefu.pnexpert.di.appModule
import ru.fefu.pnexpert.di.glueModule
import ru.fefu.sign_up_impl.di.signUpModule
import ru.fefu.video_tests_impl.di.videoTestsModule
import ru.fefu.written_test_impl.di.writtenTestModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                appModule,
                glueModule,
                coroutinesModule,
                dataModule,
                calendarModule,
                mainPageModule,
                photoTestsModule,
                signUpModule,
                videoTestsModule,
                writtenTestModule
            )
        }
    }
}
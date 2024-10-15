package ru.fefu.pnexpert.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.fefu.calendar_impl.domain.repositories.CalendarEventsRepository
import ru.fefu.pnexpert.glue.calendar.repository.CalendarEventsRepositoryImpl
import ru.fefu.pnexpert.glue.video_tests.AdapterCameraXRepository
import ru.fefu.pnexpert.glue.written_tests.repositories.AdapterWrittenTestRepository
import ru.fefu.pnexpert.presentation.BottomTabs
import ru.fefu.sign_up_impl.navigation.SignUpRouter
import ru.fefu.video_tests_impl.domain.CameraXRepository
import ru.fefu.written_test_impl.domain.WrittenTestRepository

val glueModule = module {
    single<CalendarEventsRepository> {
        CalendarEventsRepositoryImpl(get())
    }
    single<SignUpRouter> {
        object : SignUpRouter {
            override fun provideMainTabRoute() = BottomTabs.Main.route
        }
    }
    single<CameraXRepository> {
        AdapterCameraXRepository(get())
    }
    single<WrittenTestRepository> {
        AdapterWrittenTestRepository(get())
    }
}
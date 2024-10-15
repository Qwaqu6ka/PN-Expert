package ru.fefu.calendar_impl.di

import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import ru.fefu.calendar_api.CalendarApi
import ru.fefu.calendar_impl.navigation.CalendarImpl
import ru.fefu.calendar_impl.presentation.CalendarViewModel

val calendarModule = module {
    singleOf<CalendarApi>(::CalendarImpl)
    viewModelOf(::CalendarViewModel)
}
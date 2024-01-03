package ru.fefu.calendar_impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.fefu.calendar_api.CalendarApi
import ru.fefu.calendar_impl.navigation.CalendarImpl

@Module
@InstallIn(SingletonComponent::class)
interface CalendarModule {
    @Binds
    fun bindCalendar(calendarImpl: CalendarImpl): CalendarApi
}
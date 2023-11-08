package ru.fefu.calendar_impl.data.di

import ru.fefu.calendar_api.CalendarApi
import ru.fefu.calendar_impl.navigation.CalendarImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface CalendarModule {
    @Binds
    fun bindCalendar(calendarImpl: CalendarImpl): CalendarApi
}
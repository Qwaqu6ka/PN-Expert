package ru.fefu.data.calendar.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.fefu.data.calendar.repositoty.CalendarEventsDataRepository
import ru.fefu.data.calendar.repositoty.CalendarEventsDataRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface CalendarModule {

    @Binds
    fun provideCalendarEventsRep(rep: CalendarEventsDataRepositoryImpl): CalendarEventsDataRepository
}
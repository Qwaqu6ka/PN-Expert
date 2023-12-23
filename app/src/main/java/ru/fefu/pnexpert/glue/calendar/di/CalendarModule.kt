package ru.fefu.pnexpert.glue.calendar.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.fefu.calendar_impl.domain.repositories.CalendarEventsRepository
import ru.fefu.pnexpert.glue.calendar.repository.CalendarEventsRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface CalendarModule {

    @Binds
    fun provideCalendarEventsRep(rep: CalendarEventsRepositoryImpl): CalendarEventsRepository
}
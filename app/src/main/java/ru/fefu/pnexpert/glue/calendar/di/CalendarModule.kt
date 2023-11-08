package ru.fefu.pnexpert.glue.calendar.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.fefu.calendar_impl.domain.repositories.CalendarEventsRepository
import  ru.fefu.calendar_impl.domain.source.CalendarDataSourceI
import ru.fefu.pnexpert.glue.calendar.repository.CalendarEventsRepositoryImpl
import ru.fefu.pnexpert.glue.calendar.sources.CalendarDataSource

@Module
@InstallIn(SingletonComponent::class)
interface CalendarModule {

    @Binds
    fun provideDataSource(dataSourceImpl: CalendarDataSource): CalendarDataSourceI
    @Binds
    fun provideCalendarEventsRep(rep: CalendarEventsRepositoryImpl): CalendarEventsRepository
}
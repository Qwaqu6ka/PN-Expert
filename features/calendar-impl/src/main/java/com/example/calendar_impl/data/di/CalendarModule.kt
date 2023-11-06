package com.example.calendar_impl.data.di

import com.example.calendar_api.CalendarApi
import com.example.calendar_impl.data.CalendarEventsRepositoryImpl
import com.example.calendar_impl.data.source.CalendarDataSource
import com.example.calendar_impl.data.source.CalendarDataSourceI
import com.example.calendar_impl.domain.repositories.CalendarEventsRepository
import com.example.calendar_impl.navigation.CalendarImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CalendarModule {
    @Binds
    fun bindCalendar(calendarImpl: CalendarImpl): CalendarApi

    @Binds
    fun provideDataSource(dataSourceImpl:CalendarDataSource): CalendarDataSourceI
    @Binds
    fun provideCalendarEventsRep(rep:CalendarEventsRepositoryImpl): CalendarEventsRepository
}
package com.example.calendar_impl.domain.repositories
import com.example.calendar_impl.domain.models.AvailableTime
import com.example.calendar_impl.domain.models.CalendarActions
import com.example.calendar_impl.domain.models.CalendarData


interface CalendarEventsRepository {
    fun getEvents(type: CalendarActions):CalendarData

    fun getRecords():List<AvailableTime>
}
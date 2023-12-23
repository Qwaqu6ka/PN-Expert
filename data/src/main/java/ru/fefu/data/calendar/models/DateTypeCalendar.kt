package ru.fefu.data.calendar.models

data class CalendarDataModel(
    val type: CalendarActionsData,
    val data: DateEventsData
)

interface BaseEventData{
    val type: CalendarActionsData
}
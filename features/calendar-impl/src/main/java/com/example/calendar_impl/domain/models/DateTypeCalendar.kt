package com.example.calendar_impl.domain.models

data class CalendarType(
    val type: CalendarActions,
    val title:String,
    val isSelected:Boolean,
)

enum class CalendarActions{
    CONSULTATION,APPOINTMENT,EVENT,ALL
}
data class CalendarData(
    val type:CalendarActions,
    val data:DateEvents
)

interface BaseEvent{
    val type:CalendarActions
}
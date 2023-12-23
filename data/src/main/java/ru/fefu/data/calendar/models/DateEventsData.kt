package ru.fefu.data.calendar.models

import java.time.LocalDate

data class DateEventsData(
    val date: LocalDate,
    val listEvents:List<BaseEventData>
)

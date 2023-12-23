package ru.fefu.calendar_impl.domain.models

import java.time.LocalDate

data class DateEvents(
    val date: LocalDate,
    val listEvents:List<BaseEvent>
)

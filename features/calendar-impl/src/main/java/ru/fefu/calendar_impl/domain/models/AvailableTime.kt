package ru.fefu.calendar_impl.domain.models

import java.time.LocalDate

data class AvailableTime (
    val date:LocalDate,
    val timeRange: TimeRange,
    val doctorsFullName:String,
)
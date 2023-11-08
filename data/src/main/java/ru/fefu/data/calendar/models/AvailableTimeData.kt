package ru.fefu.data.calendar.models

import java.time.LocalDate

data class AvailableTimeData (
    val date:LocalDate,
    val timeRange: TimeRangeData,
    val doctorsFullName:String,
)
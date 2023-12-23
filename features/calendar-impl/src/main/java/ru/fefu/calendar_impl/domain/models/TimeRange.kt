package ru.fefu.calendar_impl.domain.models

import java.time.LocalTime

data class TimeRange(
    val startTime:LocalTime,
    val endTime:LocalTime
){
}

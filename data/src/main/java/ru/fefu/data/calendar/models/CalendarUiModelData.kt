package ru.fefu.data.calendar.models

import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class CalendarUiModelData(
    val selectedDate: DateData, // the date selected by the User. by default is Today.
    val visibleDates: List<DateData> // the dates shown on the screen
) {

    val startDate: DateData = visibleDates.first() // the first of the visible dates
    val endDate: DateData = visibleDates.last() // the last of the visible dates

    data class DateData(
        val date: LocalDate,
        val isSelected: Boolean,
        val isToday: Boolean,
        val listEvents: List<BaseEventData>
    ) {
        val day: String = date.format(DateTimeFormatter.ofPattern("E")) // get the day by formatting the date
    }
}

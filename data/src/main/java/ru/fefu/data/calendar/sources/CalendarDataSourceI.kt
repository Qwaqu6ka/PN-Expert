package ru.fefu.data.calendar.sources

import ru.fefu.data.calendar.models.BaseEventData
import ru.fefu.data.calendar.models.CalendarActionsData
import ru.fefu.data.calendar.models.CalendarUiModelData
import java.time.LocalDate


interface CalendarDataSourceI {
    val today: LocalDate


    fun getData(startDate: LocalDate = today.withDayOfMonth(1), lastSelectedDate: LocalDate, typeCalendar: CalendarActionsData): CalendarUiModelData

    fun getDatesBetween(startDate: LocalDate, endDate: LocalDate): List<LocalDate>

    fun toUiModel(
        dateList: List<LocalDate>,
        lastSelectedDate: LocalDate,
        type: CalendarActionsData,

        ): CalendarUiModelData

    fun getEvents(date:LocalDate,type: CalendarActionsData): List<BaseEventData>


    fun toItemUiModel(date: LocalDate, isSelectedDate: Boolean, listEvents: List<BaseEventData>):CalendarUiModelData.DateData
}
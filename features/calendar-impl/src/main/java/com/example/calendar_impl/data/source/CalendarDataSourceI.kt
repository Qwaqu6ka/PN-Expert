package com.example.calendar_impl.data.source

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.calendar_impl.domain.models.BaseEvent
import com.example.calendar_impl.domain.models.CalendarActions
import com.example.calendar_impl.domain.models.CalendarUiModel
import java.time.LocalDate


interface CalendarDataSourceI {
    val today: LocalDate


    fun getData(startDate: LocalDate = today.withDayOfMonth(1), lastSelectedDate: LocalDate, typeCalendar: CalendarActions): CalendarUiModel

    fun getDatesBetween(startDate: LocalDate, endDate: LocalDate): List<LocalDate>

    fun toUiModel(
        dateList: List<LocalDate>,
        lastSelectedDate: LocalDate,
        type: CalendarActions

    ): CalendarUiModel

    fun getEvents(date: LocalDate, type: CalendarActions): SnapshotStateList<BaseEvent>

    fun toItemUiModel(date: LocalDate, isSelectedDate: Boolean, listEvents: SnapshotStateList<BaseEvent>):CalendarUiModel.Date
}
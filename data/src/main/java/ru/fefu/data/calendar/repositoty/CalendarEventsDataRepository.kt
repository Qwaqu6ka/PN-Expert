package ru.fefu.data.calendar.repositoty
import ru.fefu.data.calendar.models.AvailableTimeData
import ru.fefu.data.calendar.models.BaseEventData
import ru.fefu.data.calendar.models.CalendarActionsData
import ru.fefu.data.calendar.models.CalendarUiModelData
import java.time.LocalDate


interface CalendarEventsDataRepository {
    fun getRecords():List<AvailableTimeData>

    fun getData(startDate: LocalDate = LocalDate.now(), lastSelectedDate: LocalDate, typeCalendar: CalendarActionsData): CalendarUiModelData

    fun getDatesBetween(startDate: LocalDate, endDate: LocalDate): List<LocalDate>

    fun toUiModel(
        dateList: List<LocalDate>,
        lastSelectedDate: LocalDate,
        type: CalendarActionsData,

        ): CalendarUiModelData

    fun getEvents(date:LocalDate,type: CalendarActionsData): List<BaseEventData>


    fun toItemUiModel(date: LocalDate, isSelectedDate: Boolean, listEvents: List<BaseEventData>):CalendarUiModelData.DateData
}
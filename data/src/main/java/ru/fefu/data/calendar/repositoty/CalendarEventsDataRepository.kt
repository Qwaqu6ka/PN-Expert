package ru.fefu.data.calendar.repositoty
import ru.fefu.data.calendar.models.AvailableTimeData
import ru.fefu.data.calendar.models.CalendarActionsData
import ru.fefu.data.calendar.models.CalendarUiModelData
import java.time.LocalDate


interface CalendarEventsDataRepository {
    //fun  getEvents(date:LocalDate,type: CalendarActions): List<BaseEvent>

    fun getRecords():List<AvailableTimeData>

    fun getData(startDate: LocalDate = LocalDate.now(), lastSelectedDate: LocalDate, typeCalendar: CalendarActionsData): CalendarUiModelData
}
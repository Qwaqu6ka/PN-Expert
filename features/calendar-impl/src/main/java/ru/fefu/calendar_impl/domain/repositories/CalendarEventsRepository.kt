package ru.fefu.calendar_impl.domain.repositories
import ru.fefu.calendar_impl.domain.models.AvailableTime
import ru.fefu.calendar_impl.domain.models.CalendarActions
import ru.fefu.calendar_impl.domain.models.CalendarUiModel
import java.time.LocalDate


interface CalendarEventsRepository {
    //fun  getEvents(date:LocalDate,type: CalendarActions): List<BaseEvent>

    fun getRecords():List<AvailableTime>

    fun getData(startDate: LocalDate = LocalDate.now(), lastSelectedDate: LocalDate, typeCalendar: CalendarActions): CalendarUiModel
}
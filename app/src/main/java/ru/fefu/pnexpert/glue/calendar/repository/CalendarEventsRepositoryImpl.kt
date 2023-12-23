package ru.fefu.pnexpert.glue.calendar.repository

import ru.fefu.calendar_impl.domain.models.AvailableTime
import ru.fefu.calendar_impl.domain.models.CalendarActions
import ru.fefu.calendar_impl.domain.models.CalendarUiModel
import ru.fefu.calendar_impl.domain.repositories.CalendarEventsRepository
import ru.fefu.data.calendar.repositoty.CalendarEventsDataRepository
import ru.fefu.pnexpert.glue.calendar.toAvailableTime
import ru.fefu.pnexpert.glue.calendar.toCalendarActionsData
import ru.fefu.pnexpert.glue.calendar.toCalendarUiModel
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CalendarEventsRepositoryImpl @Inject constructor(
    private val calendarEventsRepository: CalendarEventsDataRepository
) : CalendarEventsRepository {
    override fun getRecords(): List<AvailableTime> {
        return calendarEventsRepository.getRecords().map {
            it.toAvailableTime()
        }
    }

    override fun getData(
        startDate: LocalDate,
        lastSelectedDate: LocalDate,
        typeCalendar: CalendarActions
    ): CalendarUiModel {
        return calendarEventsRepository.getData(
            startDate,
            lastSelectedDate,
            typeCalendar.toCalendarActionsData()
        ).toCalendarUiModel()
    }

}


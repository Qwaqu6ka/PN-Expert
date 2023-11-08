package ru.fefu.pnexpert.glue.calendar.sources

import ru.fefu.calendar_impl.domain.models.BaseEvent
import ru.fefu.calendar_impl.domain.models.CalendarActions
import ru.fefu.calendar_impl.domain.models.CalendarUiModel
import ru.fefu.data.calendar.models.BaseEventData
import ru.fefu.data.calendar.sources.CalendarDataSource
import ru.fefu.calendar_impl.domain.source.CalendarDataSourceI
import ru.fefu.pnexpert.glue.calendar.toCalendarActionsData
import ru.fefu.pnexpert.glue.calendar.toCalendarUiModel
import ru.fefu.pnexpert.glue.calendar.toDate
import java.time.LocalDate
import javax.inject.Inject

class CalendarDataSource @Inject constructor(
    private val calendarDataSource: CalendarDataSource
) : CalendarDataSourceI {

    override val today: LocalDate
        get() {
            return calendarDataSource.today
        }

    override fun getData(
        startDate: LocalDate,
        lastSelectedDate: LocalDate,
        typeCalendar: CalendarActions
    ): CalendarUiModel {
        return calendarDataSource.getData(
            startDate,
            lastSelectedDate,
            typeCalendar.toCalendarActionsData()
        ).toCalendarUiModel()
    }

    override fun getDatesBetween(startDate: LocalDate, endDate: LocalDate): List<LocalDate> {
        return calendarDataSource.getDatesBetween(startDate, endDate)
    }

    override fun toUiModel(
        dateList: List<LocalDate>,
        lastSelectedDate: LocalDate,
        type: CalendarActions
    ): CalendarUiModel {
        return calendarDataSource.toUiModel(
            dateList,
            lastSelectedDate,
            type.toCalendarActionsData()
        ).toCalendarUiModel()
    }

    override fun getEvents(date: LocalDate, type: CalendarActions): List<BaseEvent> {
        return calendarDataSource.getEvents(date, type.toCalendarActionsData()).map {
            it as BaseEvent
        }
    }

    override fun toItemUiModel(
        date: LocalDate,
        isSelectedDate: Boolean,
        listEvents: List<BaseEvent>
    ): CalendarUiModel.Date {
        return calendarDataSource.toItemUiModel(date, isSelectedDate, listEvents.map {
            it as BaseEventData
        }).toDate()
    }

}
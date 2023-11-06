package com.example.calendar_impl.data.source

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import com.example.calendar_impl.domain.models.BaseEvent
import com.example.calendar_impl.domain.models.CalendarActions
import com.example.calendar_impl.domain.models.CalendarUiModel
import com.example.calendar_impl.domain.repositories.CalendarEventsRepository
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.stream.Collectors
import java.util.stream.Stream
import javax.inject.Inject

class CalendarDataSource @Inject constructor(
    private val repository: CalendarEventsRepository
):CalendarDataSourceI {
    override val today: LocalDate
        get() {
            return LocalDate.now()
        }


    override fun getData(startDate: LocalDate, lastSelectedDate: LocalDate,typeCalendar: CalendarActions): CalendarUiModel {
        val firstDayOfMonth = startDate.withMonth(startDate.month.value)
        val endDayOfMonth = firstDayOfMonth.plusDays((firstDayOfMonth.lengthOfMonth() - 1).toLong())
        val visibleDates = getDatesBetween(firstDayOfMonth, endDayOfMonth.plusDays(1))
        return toUiModel(visibleDates, lastSelectedDate,typeCalendar)
    }

    override  fun getDatesBetween(startDate: LocalDate, endDate: LocalDate): List<LocalDate> {
        val numOfDays = ChronoUnit.DAYS.between(startDate, endDate)
        return Stream.iterate(startDate) { date ->
            date.plusDays(/* daysToAdd = */ 1)
        }
            .limit(numOfDays)
            .collect(Collectors.toList())
    }

    override fun toUiModel(
        dateList: List<LocalDate>,
        lastSelectedDate: LocalDate,
        type: CalendarActions

    ): CalendarUiModel {
        return CalendarUiModel(
            selectedDate = toItemUiModel(lastSelectedDate, true,getEvents(lastSelectedDate,type)),
            visibleDates = dateList.map {
                toItemUiModel(it, it.isEqual(lastSelectedDate),getEvents(it,type))
            },
        )
    }

    override fun getEvents(date: LocalDate, type: CalendarActions): SnapshotStateList<BaseEvent> {
        val data = repository.getEvents(type)
        return if(data.data.date == date){
            data.data.listEvents.toMutableStateList()
        }else{
            mutableStateListOf()
        }
    }

    override fun toItemUiModel(date: LocalDate, isSelectedDate: Boolean,listEvents: SnapshotStateList<BaseEvent>) = CalendarUiModel.Date(
        isSelected = isSelectedDate,
        isToday = date.isEqual(today),
        date = date,
        listEvents = listEvents
    )
}
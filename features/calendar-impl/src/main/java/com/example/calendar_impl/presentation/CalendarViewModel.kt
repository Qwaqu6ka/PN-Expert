package com.example.calendar_impl.presentation

import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.calendar_impl.data.source.CalendarDataSourceI
import com.example.calendar_impl.domain.models.AvailableTime
import com.example.calendar_impl.domain.models.CalendarActions
import com.example.calendar_impl.domain.models.CalendarType
import com.example.calendar_impl.domain.repositories.CalendarEventsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel@Inject constructor(
    private val dataSource: CalendarDataSourceI,
    private val rep:CalendarEventsRepository
):ViewModel() {
    val calendarDataSource = dataSource
    val calendarRepository = rep // hilt
    val activeType: MutableIntState = mutableIntStateOf(0)
    var openBottomSheetPills = mutableStateOf(false)
    var openBottomSheetTimes  = mutableStateOf(false)
    val typesCalendar = mutableStateListOf<CalendarType>(
        CalendarType(
            CalendarActions.ALL,
            "Личный календарь",
            true
        ),
        CalendarType(
            CalendarActions.CONSULTATION,
            "Запись на онлайн консультацию",
            false
        ),
        CalendarType(
            CalendarActions.APPOINTMENT,
            "Запись на прием",
            false
        ),
        CalendarType(
            CalendarActions.EVENT,
            "Мероприятия",
            false
        )
    )
    var calendarUiModel = mutableStateOf(
            dataSource.getData(
                lastSelectedDate = dataSource.today,
                typeCalendar = typesCalendar[activeType.intValue].type
            )
        )

    var selectedDate = mutableStateOf(calendarUiModel.value.selectedDate.date)

    var clickableEventElementIndex= mutableStateOf<Int?>(null)
    var clickableTimesElementIndex= mutableStateOf<Int?>(null)

    fun chooseType(index:Int) {
        typesCalendar[activeType.intValue] = typesCalendar[activeType.intValue].copy(isSelected = false)
        typesCalendar[index] = typesCalendar[index].copy(isSelected = true)
        activeType.intValue = index
    }

    fun getAvailableTime(date: LocalDate):List<AvailableTime>{
        val list = calendarRepository.getRecords()
        return list.filter { it.date == date }
    }
}
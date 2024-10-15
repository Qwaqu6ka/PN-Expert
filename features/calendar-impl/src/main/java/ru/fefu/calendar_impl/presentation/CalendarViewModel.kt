package ru.fefu.calendar_impl.presentation

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ru.fefu.calendar_impl.R
import ru.fefu.calendar_impl.domain.models.AvailableTime
import ru.fefu.calendar_impl.domain.models.BaseEvent
import ru.fefu.calendar_impl.domain.models.BookingModel
import ru.fefu.calendar_impl.domain.models.BookingStatus
import ru.fefu.calendar_impl.domain.models.BookingStatusEntity
import ru.fefu.calendar_impl.domain.models.CalendarActions
import ru.fefu.calendar_impl.domain.models.CalendarType
import ru.fefu.calendar_impl.domain.models.CalendarUiModel
import ru.fefu.calendar_impl.domain.models.PillModel
import ru.fefu.calendar_impl.domain.models.TaskModel
import ru.fefu.calendar_impl.domain.models.TestModel
import ru.fefu.calendar_impl.domain.models.TimeRange
import ru.fefu.calendar_impl.domain.repositories.CalendarEventsRepository
import ru.fefu.photo_tests_api.PhotoTestsApi
import ru.fefu.written_test_api.WrittenTestApi
import java.time.LocalDate

class CalendarViewModel(
    private val calendarEventsRepository: CalendarEventsRepository,
    private val photoTestsApi: PhotoTestsApi,
    private val writtenTestApi: WrittenTestApi,
    application: Context,   // todo убарть
) : ViewModel() {
    private val calendarRepository = calendarEventsRepository
    var indexOfActiveType by mutableIntStateOf(0)
    var openBottomSheetPills by mutableStateOf(false)
    var openBottomSheetTimes by mutableStateOf(false)
    val typesCalendar = mutableStateListOf(
        CalendarType(
            type = CalendarActions.ALL,
            title = application.getString(R.string.user_calendar),
            isSelected = true
        ),
        CalendarType(
            type = CalendarActions.CONSULTATION,
            title = application.getString(R.string.consultation_calendar),
            isSelected = false
        ),
        CalendarType(
            type = CalendarActions.APPOINTMENT,
            title = application.getString(R.string.appointment_calendar),
            isSelected = false
        ),
        CalendarType(
            type = CalendarActions.EVENT,
            title = application.getString(R.string.event_calendar),
            isSelected = false
        )
    )
    var calendarState by mutableStateOf(
        calendarEventsRepository.getData(
            lastSelectedDate = LocalDate.now(),
            typeCalendar = typesCalendar[indexOfActiveType].type
        )
    )

    private var selectedDate by mutableStateOf(calendarState.selectedDate.date)

    var clickableEventElementIndex by mutableStateOf<Int?>(null)
    var clickableTimesElementIndex by mutableStateOf<Int?>(null)


    fun chooseTypeCalendar(index: Int) {
        typesCalendar[indexOfActiveType] = typesCalendar[indexOfActiveType].copy(isSelected = false)
        typesCalendar[index] = typesCalendar[index].copy(isSelected = true)
        indexOfActiveType = index
    }

    fun getAvailableTime(): List<AvailableTime> {
        val date = selectedDate
        val list = calendarRepository.getRecords()
        return list.filter { it.date == date }
    }

    fun updateUiModelData(type: CalendarActions) {
        calendarState.endDate
        calendarState = calendarEventsRepository.getData(
            startDate = calendarState.startDate.date,
            lastSelectedDate = calendarState.selectedDate.date,
            typeCalendar = type
        )
    }

    fun onNextMonthClickListener(date: LocalDate, type: CalendarActions) {
        calendarState = calendarEventsRepository.getData(
            startDate = date.plusMonths(1).withDayOfMonth(1),
            lastSelectedDate = calendarState.selectedDate.date,
            typeCalendar = type
        )
    }

    fun onPrevMonthClickListener(date: LocalDate, type: CalendarActions) {
        calendarState = calendarEventsRepository.getData(
            startDate = date.minusMonths(1).withDayOfMonth(1),
            lastSelectedDate = calendarState.selectedDate.date,
            typeCalendar = type
        )
    }

    fun onDateClickListener(date: CalendarUiModel.Date) {
        calendarState = calendarState.copy(
            selectedDate = date,
            visibleDates = calendarState.visibleDates.map {
                it.copy(
                    isSelected = it.date.isEqual(date.date)
                )
            }
        )
        selectedDate = date.date
    }

    fun onClickPillsEvent(it: Int) {
        changeBottomSheetPillsState()
        clickableEventElementIndex = it
    }

    fun onClickTimes(it: Int) {
        changeBottomSheetTimesState()
        clickableTimesElementIndex = it
    }

    fun setEventStateTrue(index: Int) {

        calendarState.selectedDate.listEvents.set(
            index,
            if (calendarState.selectedDate.listEvents[index] is TaskModel) {
                (calendarState.selectedDate.listEvents[index] as TaskModel).copy(
                    isCompleted = true
                )
            } else {
                (calendarState.selectedDate.listEvents[index] as PillModel).copy(
                    isCompleted = true
                )
            }
        )
    }

    fun onApplyBottomSheetPills() {
        changeBottomSheetPillsState()
        setEventStateTrue(clickableEventElementIndex!!)
    }

    fun onApplyBottomSheetTimes(title: String, time: TimeRange) {
        changeBottomSheetTimesState()
        addEvent(
            BookingModel(
                title = title,
                status = BookingStatus(
                    type = BookingStatusEntity.CONFIRMATION,
                    comment = null
                ),
                time = time,
                type = CalendarActions.CONSULTATION
            )
        )
    }

    private fun addEvent(item: BaseEvent) {
        calendarState.selectedDate.listEvents.add(item)
    }

    fun changeBottomSheetPillsState() {
        openBottomSheetPills = !openBottomSheetPills
    }

    fun changeBottomSheetTimesState() {
        openBottomSheetTimes = !openBottomSheetTimes
    }

    fun navigateTask(
        index: Int,
        route: TestModel,
        onTaskNavigate: (String) -> Unit,
    ) {
        setEventStateTrue(index)
        onTaskNavigate(testMapper(route))

    }

    private fun testMapper(str: TestModel): String {
        return when (str) {
            TestModel.CLOCK_PHOTOTEST -> {
                photoTestsApi.clockPhotoTestRoute
            }

            TestModel.FACE_PHOTOTEST -> {
                photoTestsApi.facePhotoTestRoute
            }

            TestModel.FULL_LENGTH_PHOTOTEST -> {
                photoTestsApi.fullLengthPhotoTestRoute
            }

            TestModel.HANDWRITING_PHOTOTEST -> {
                photoTestsApi.handwritingPhotoTestRoute
            }

            TestModel.TESTUPDRS1ROUTE -> {
                writtenTestApi.testUpdrs1Route
            }

            TestModel.TESTUPDRS2ROUTE -> {
                writtenTestApi.testUpdrs2Route
            }

            TestModel.TESTUPDRS3ROUTE -> {
                writtenTestApi.testUpdrs3Route
            }

            TestModel.TESTUPDRS4ROUTE -> {
                writtenTestApi.testUpdrs4Route
            }

            TestModel.TESTPDQ39ROUTE -> {
                writtenTestApi.testPdq39Route
            }

            TestModel.TESTHADSROUTE -> {
                writtenTestApi.testHadsRoute
            }

            TestModel.TESTSCHAWABENGLANDROUTE -> {
                writtenTestApi.testSchwabEnglandRoute
            }

            TestModel.TESTHOENYAHRROUTE -> {
                writtenTestApi.testHoehnYahrRoute
            }

            TestModel.TESTFABROUTE -> {
                writtenTestApi.testFabRoute
            }

            TestModel.TESTPSQIROUTE -> {
                writtenTestApi.testPsqiRoute
            }
        }
    }

}
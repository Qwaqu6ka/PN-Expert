package ru.fefu.calendar_impl.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ru.fefu.calendar_impl.domain.repositories.CalendarEventsRepository
import ru.fefu.calendar_impl.domain.models.TaskModel
import ru.fefu.calendar_impl.domain.models.AvailableTime
import ru.fefu.calendar_impl.domain.models.BaseEvent
import ru.fefu.calendar_impl.domain.models.BookingModel
import ru.fefu.calendar_impl.domain.models.BookingStatus
import ru.fefu.calendar_impl.domain.models.BookingStatusEntity
import ru.fefu.calendar_impl.domain.models.CalendarActions
import ru.fefu.calendar_impl.domain.models.CalendarType
import ru.fefu.calendar_impl.domain.models.CalendarUiModel
import ru.fefu.calendar_impl.domain.models.TimeRange
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.fefu.calendar_impl.domain.models.PillModel
import ru.fefu.calendar_impl.domain.models.TestModel
import ru.fefu.photo_tests_api.PhotoTestsApi
import ru.fefu.written_test_api.WrittenTestApi
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel@Inject constructor(
    private val rep: CalendarEventsRepository,
    private val photoTestsApi: PhotoTestsApi,
    private val writtenTestApi: WrittenTestApi,
):ViewModel() {
    private val calendarRepository = rep
    var activeType  by mutableIntStateOf(0)
    var openBottomSheetPills by mutableStateOf(false)
    var openBottomSheetTimes  by mutableStateOf(false)
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
    var calendarUiModel by mutableStateOf(
            rep.getData(
                lastSelectedDate = LocalDate.now(),
                typeCalendar = typesCalendar[activeType].type
            )
        )

    private var selectedDate by mutableStateOf(calendarUiModel.selectedDate.date)

    var clickableEventElementIndex by mutableStateOf<Int?>(null)
    var clickableTimesElementIndex by mutableStateOf<Int?>(null)

    fun chooseType(index:Int) {
        typesCalendar[activeType] = typesCalendar[activeType].copy(isSelected = false)
        typesCalendar[index] = typesCalendar[index].copy(isSelected = true)
        activeType = index
    }

    fun getAvailableTime():List<AvailableTime>{
        val date = selectedDate
        val list = calendarRepository.getRecords()
        return list.filter {it.date == date}
    }
    fun updateUiModelData(type: CalendarActions){
        calendarUiModel.endDate
        calendarUiModel = rep.getData(
                        startDate = calendarUiModel.startDate.date,
                        lastSelectedDate = calendarUiModel.selectedDate.date,
                        typeCalendar = type
                    )
    }

    fun onNextMonthClickListener(date:LocalDate,type: CalendarActions){
        calendarUiModel = rep.getData(
            startDate = date.plusMonths(1).withDayOfMonth(1),
            lastSelectedDate = calendarUiModel.selectedDate.date,
            typeCalendar = type
        )
    }

    fun onPrevMonthClickListener(date:LocalDate,type: CalendarActions){
        calendarUiModel = rep.getData(
            startDate = date.minusMonths(1).withDayOfMonth(1),
            lastSelectedDate = calendarUiModel.selectedDate.date,
            typeCalendar = type
        )
    }
    fun onDateClickListener(date: CalendarUiModel.Date){
        calendarUiModel = calendarUiModel.copy(
            selectedDate = date,
            visibleDates = calendarUiModel.visibleDates.map {
                it.copy(
                    isSelected = it.date.isEqual(date.date)
                )
            }
        )
        selectedDate = date.date
    }

    fun onClickPillsEvent(it:Int){
        changeBottomSheetPillsState()
        clickableEventElementIndex = it
    }

    fun onClickTimes(it:Int){
        changeBottomSheetTimesState()
        clickableTimesElementIndex = it
    }

    fun setEventStateTrue(index:Int){

        calendarUiModel.selectedDate.listEvents.set(index,
            if(calendarUiModel.selectedDate.listEvents[index] is TaskModel){
            (calendarUiModel.selectedDate.listEvents[index] as TaskModel).copy(
                isCompleted = true
            )
        }else{
            (calendarUiModel.selectedDate.listEvents[index] as PillModel).copy(
                isCompleted = true
            )
        }
        )
    }

    fun onApplyBottomSheetPills(){
        changeBottomSheetPillsState()
        setEventStateTrue(clickableEventElementIndex!!)
    }
    fun onApplyBottomSheetTimes(title:String,time: TimeRange){
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

    private fun addEvent(item: BaseEvent){
        calendarUiModel.selectedDate.listEvents.add(item)
    }

    fun changeBottomSheetPillsState(){
        openBottomSheetPills = !openBottomSheetPills
    }
    fun changeBottomSheetTimesState(){
        openBottomSheetTimes = !openBottomSheetTimes
    }

    fun navigateTask(
        index: Int,
        route:TestModel,
        onTaskNavigate:(String)->Unit,
    ){
        setEventStateTrue(index)
        onTaskNavigate(testMapper(route))

    }
    private fun testMapper(str:TestModel):String{
        return when(str){
            TestModel.PHOTOTEST->{
                photoTestsApi.route
            }
            TestModel.TESTUPDRS1ROUTE->{
                writtenTestApi.testUpdrs1Route
            }
            TestModel.TESTUPDRS2ROUTE->{
                writtenTestApi.testUpdrs2Route
            }
            TestModel.TESTUPDRS3ROUTE->{
                writtenTestApi.testUpdrs3Route
            }
            TestModel.TESTUPDRS4ROUTE->{
                writtenTestApi.testUpdrs4Route
            }
            TestModel.TESTPDQ39ROUTE->{
                writtenTestApi.testPdq39Route
            }
            TestModel.TESTHADSROUTE->{
                writtenTestApi.testHadsRoute
            }
            TestModel.TESTSCHAWABENGLANDROUTE->{
                writtenTestApi.testSchwabEnglandRoute
            }
            TestModel.TESTHOENYAHRROUTE->{
                writtenTestApi.testHoehnYahrRoute
            }
            TestModel.TESTFABROUTE->{
                writtenTestApi.testFabRoute
            }
            TestModel.TESTPSQIROUTE->{
                writtenTestApi.testPsqiRoute
            }
        }
    }

}
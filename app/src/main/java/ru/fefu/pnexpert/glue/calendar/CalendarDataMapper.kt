package ru.fefu.pnexpert.glue.calendar

import androidx.compose.runtime.toMutableStateList
import ru.fefu.calendar_impl.domain.models.TaskModel
import ru.fefu.calendar_impl.domain.models.AvailableTime
import ru.fefu.calendar_impl.domain.models.BaseEvent
import ru.fefu.calendar_impl.domain.models.BookingModel
import ru.fefu.calendar_impl.domain.models.BookingStatus
import ru.fefu.calendar_impl.domain.models.BookingStatusEntity
import ru.fefu.calendar_impl.domain.models.CalendarActions
import ru.fefu.calendar_impl.domain.models.CalendarData
import ru.fefu.calendar_impl.domain.models.CalendarUiModel
import ru.fefu.calendar_impl.domain.models.DateEvents
import ru.fefu.calendar_impl.domain.models.EventType
import ru.fefu.calendar_impl.domain.models.PillModel
import ru.fefu.calendar_impl.domain.models.TestModel
import ru.fefu.calendar_impl.domain.models.TimeRange
import ru.fefu.data.calendar.models.AvailableTimeData
import ru.fefu.data.calendar.models.BaseEventData
import ru.fefu.data.calendar.models.BookingModelData
import ru.fefu.data.calendar.models.BookingStatusData
import ru.fefu.data.calendar.models.BookingStatusEntityData
import ru.fefu.data.calendar.models.CalendarActionsData
import ru.fefu.data.calendar.models.CalendarDataModel
import ru.fefu.data.calendar.models.CalendarUiModelData
import ru.fefu.data.calendar.models.DateEventsData
import ru.fefu.data.calendar.models.EventTypeData
import ru.fefu.data.calendar.models.PillModelData
import ru.fefu.data.calendar.models.TaskModelData
import ru.fefu.data.calendar.models.TimeRangeData


fun EventTypeData.toEventType(): EventType {
    return when (this) {
        EventTypeData.ACTION -> {
            EventType.ACTION
        }

        EventTypeData.PILL -> {
            EventType.PILL
        }
    }
}

fun CalendarActionsData.toCalendarActions(): CalendarActions {
    return when (this) {
        CalendarActionsData.ALL -> {
            CalendarActions.ALL
        }

        CalendarActionsData.CONSULTATION -> {
            CalendarActions.CONSULTATION
        }

        CalendarActionsData.APPOINTMENT -> {
            CalendarActions.APPOINTMENT
        }

        CalendarActionsData.EVENT -> {
            CalendarActions.EVENT
        }
    }
}



fun TaskModelData.toTaskModel():TaskModel {
    return TaskModel(
        event = this.event.toEventType(),
        title = this.title,
        route = testMapper(this.route),
        isCompleted = this.isCompleted,
        type = this.type.toCalendarActions()
    )
}

fun testMapper(data:String):TestModel{
    return when(data){
        "photo_test" -> TestModel.PHOTOTEST
        "testUpdrs1Route" -> TestModel.TESTUPDRS1ROUTE
        "testUpdrs2Route" -> TestModel.TESTUPDRS2ROUTE
        "testUpdrs3Route" -> TestModel.TESTUPDRS3ROUTE
        "testUpdrs4Route" -> TestModel.TESTUPDRS4ROUTE
        "testPdq39Route" -> TestModel.TESTPDQ39ROUTE
        "testHadsRoute" -> TestModel.TESTHADSROUTE
        "testSchwabEnglandRoute" -> TestModel.TESTSCHAWABENGLANDROUTE
        "testHoehnYahrRoute" -> TestModel.TESTHOENYAHRROUTE
        "testFabRoute" -> TestModel.TESTFABROUTE
        "testPsqiRoute" -> TestModel.TESTPSQIROUTE
        else-> TestModel.PHOTOTEST
    }
}
fun PillModelData.toPillModel(): PillModel {
    return PillModel(
        event = this.event.toEventType(),
        title = this.title,
        dosage = this.dosage,
        isCompleted = this.isCompleted,
        type = this.type.toCalendarActions()
    )
}

fun TimeRangeData.toTimeRange(): TimeRange {
    return TimeRange(
        startTime = this.startTime,
        endTime = this.endTime
    )
}

fun AvailableTimeData.toAvailableTime(): AvailableTime {
    return AvailableTime(
        date = this.date,
        timeRange = this.timeRange.toTimeRange(),
        doctorsFullName = this.doctorsFullName
    )
}

fun BookingStatusEntityData.toBookingStatusEntity(): BookingStatusEntity {
    return when (this) {
        BookingStatusEntityData.CONFIRMATION -> {
            BookingStatusEntity.CONFIRMATION
        }

        BookingStatusEntityData.CONFIRMED -> {
            BookingStatusEntity.CONFIRMED
        }

        BookingStatusEntityData.REJECTED -> {
            BookingStatusEntity.REJECTED
        }
    }
}

fun BookingStatusData.toBookingStatus(): BookingStatus {
    return BookingStatus(
        type = this.type.toBookingStatusEntity(),
        comment = this.comment
    )
}

fun BookingModelData.toBookingModel(): BookingModel {
    return BookingModel(
        status = this.status.toBookingStatus(),
        title = this.title,
        time = this.time.toTimeRange(),
        type = this.type.toCalendarActions(),
    )
}


fun List<BaseEventData>.toListBaseEvent(): List<BaseEvent> {
     return  this.map {
        if(it.type == CalendarActionsData.EVENT){
            try {
                val item = it as TaskModelData
                item.toTaskModel()
            }catch (e:Exception){
                val item = it as PillModelData
                item.toPillModel()
            }
        } else{
            val item = it as BookingModelData
            item.toBookingModel()
        }
     }
}

fun CalendarUiModelData.DateData.toDate(): CalendarUiModel.Date {
    return CalendarUiModel.Date(
        date = this.date,
        isSelected = this.isSelected,
        isToday = this.isToday,
        listEvents = this.listEvents.toListBaseEvent().toMutableStateList()
    )
}

fun CalendarUiModelData.toCalendarUiModel(): CalendarUiModel {
    return CalendarUiModel(
        selectedDate = this.selectedDate.toDate(),
        visibleDates = this.visibleDates.map {
            it.toDate()
        }
    )
}

fun DateEventsData.toDateEvents(): DateEvents {
    return DateEvents(
        date = this.date,
        listEvents = this.listEvents.toListBaseEvent()
    )
}


fun CalendarActions.toCalendarActionsData(): CalendarActionsData {
    return when (this) {
        CalendarActions.ALL -> {
            CalendarActionsData.ALL
        }

        CalendarActions.CONSULTATION -> {
            CalendarActionsData.CONSULTATION
        }

        CalendarActions.APPOINTMENT -> {
            CalendarActionsData.APPOINTMENT
        }

        CalendarActions.EVENT -> {
            CalendarActionsData.EVENT
        }
    }
}
package ru.fefu.data.calendar.repositoty

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
import java.time.LocalDate
import java.time.LocalTime
import java.time.temporal.ChronoUnit
import java.util.stream.Collectors
import java.util.stream.Stream
import javax.inject.Inject

class CalendarEventsDataRepositoryImpl@Inject constructor(
//    private val dataSource: CalendarDataSourceI
): CalendarEventsDataRepository {
//    override fun getEvents(date:LocalDate,type: CalendarActions): List<BaseEvent> {
//        return dataSource.getEvents(date,type)
//    }

    override fun getRecords(): List<AvailableTimeData> {
        return listOf(
            AvailableTimeData(
                date =LocalDate.of(2023, 11, 5),
                timeRange = TimeRangeData(
                    LocalTime.of(12,0),
                    LocalTime.of(14,0),
                ),
                doctorsFullName = "Криспин Анатолий Алексеевич"
            ),
            AvailableTimeData(
                date =LocalDate.of(2023, 11, 6),
                timeRange = TimeRangeData(
                    LocalTime.of(16,0),
                    LocalTime.of(17,0),
                ),
                doctorsFullName = "Криспин Анатолий Алексеевич"
            )
        )
    }

    override fun getData(
        startDate: LocalDate,
        lastSelectedDate: LocalDate,
        typeCalendar: CalendarActionsData
    ): CalendarUiModelData {
        val firstDayOfMonth = startDate.withMonth(startDate.month.value).withDayOfMonth(1)
        val endDayOfMonth = firstDayOfMonth.plusDays((firstDayOfMonth.lengthOfMonth()).toLong())
        val visibleDates = getDatesBetween(firstDayOfMonth, endDayOfMonth.plusDays(1))
        return toUiModel(visibleDates, lastSelectedDate,typeCalendar)
    }

    override fun getDatesBetween(startDate: LocalDate, endDate: LocalDate): List<LocalDate> {
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
        type: CalendarActionsData
    ): CalendarUiModelData {
        return CalendarUiModelData(
            selectedDate = toItemUiModel(lastSelectedDate, true,getEvents(lastSelectedDate,type)),
            visibleDates = dateList.map {
                toItemUiModel(it, it.isEqual(lastSelectedDate),getEvents(it,type))
            },
        )
    }

    override fun getEvents(date: LocalDate, type: CalendarActionsData): List<BaseEventData> {
        when (type) {
            CalendarActionsData.APPOINTMENT -> {
                val item = CalendarDataModel(
                    type = CalendarActionsData.APPOINTMENT,
                    data = DateEventsData(
                        date = LocalDate.of(2023, 11, 3),
                        listEvents = listOf(
                            BookingModelData(
                                status = BookingStatusData(
                                    type = BookingStatusEntityData.CONFIRMATION,
                                    comment = null
                                ),
                                title = "Давыдов Антон Игоревич",
                                time = TimeRangeData(LocalTime.of(12,0), LocalTime.of(13,0)),
                                type = CalendarActionsData.APPOINTMENT
                            )
                        )
                    )
                )
                return if(item.data.date== date){
                    item.data.listEvents.toList()
                }else{
                    listOf<BaseEventData>()
                }
            }

            CalendarActionsData.CONSULTATION -> {
                val item = CalendarDataModel(
                    type = CalendarActionsData.APPOINTMENT,
                    data = DateEventsData(
                        date = LocalDate.of(2023, 11, 10),
                        listEvents = listOf(
                            BookingModelData(
                                status = BookingStatusData(
                                    type = BookingStatusEntityData.CONFIRMED,
                                    comment = null
                                ),
                                title = "Швецова Софья Максимовна ",
                                time = TimeRangeData(LocalTime.of(15,0), LocalTime.of(16,0)),
                                type = CalendarActionsData.CONSULTATION
                            )
                        )
                    )
                )
                return if(item.data.date== date){
                    item.data.listEvents.toList()
                }else{
                    listOf<BaseEventData>()
                }
            }

            CalendarActionsData.EVENT -> {
                val item = CalendarDataModel(
                    type = CalendarActionsData.EVENT,
                    data = DateEventsData(
                        date = LocalDate.of(2023, 11, 5),
                        listEvents = listOf(
                            TaskModelData(
                                event = EventTypeData.ACTION,
                                title = "Тест: Updrs1",
                                route = "testUpdrs1Route",
                                isCompleted = false,
                                type = CalendarActionsData.EVENT
                            ),
                            TaskModelData(
                                event = EventTypeData.ACTION,
                                title = "Упражнение: Нарисовать часы",
                                route = "photo_test",
                                isCompleted = true,
                                type = CalendarActionsData.EVENT

                            ),
                            PillModelData(
                                event = EventTypeData.PILL,
                                title = "Лисиноприл",
                                dosage = "500 мг, 1 капсула",
                                isCompleted = false,
                                type = CalendarActionsData.EVENT
                            )
                        )
                    )
                )
                return if(item.data.date== date){
                    item.data.listEvents.toList()
                }else{
                    listOf<BaseEventData>()
                }
            }

            CalendarActionsData.ALL -> {
                val item = CalendarDataModel(
                    type = CalendarActionsData.ALL,
                    data = DateEventsData(
                        date = LocalDate.of(2023, 11, 3),
                        listEvents = listOf(
                            BookingModelData(
                                status = BookingStatusData(
                                    type = BookingStatusEntityData.REJECTED,
                                    comment = "Предлагаю 15:00 - 16:00"
                                ),
                                title = "Давыдов Антон Игоревич",
                                time = TimeRangeData(LocalTime.of(12,0), LocalTime.of(13,0)),
                                type = CalendarActionsData.APPOINTMENT
                            ),
                            BookingModelData(
                                status = BookingStatusData(
                                    type = BookingStatusEntityData.CONFIRMATION,
                                    comment = null
                                ),
                                title = "Швецова Софья Максимовна ",
                                time = TimeRangeData(LocalTime.of(15,0), LocalTime.of(16,0)),
                                type = CalendarActionsData.CONSULTATION
                            ),
                            TaskModelData(
                                event = EventTypeData.ACTION,
                                title = "Тест: Updrs1",
                                route = "testUpdrs1Route",
                                isCompleted = false,
                                type = CalendarActionsData.EVENT
                            ),
                            TaskModelData(
                                event = EventTypeData.ACTION,
                                title = "Упражнение: Нарисовать часы",
                                route = "photo_test",
                                isCompleted = true,
                                type = CalendarActionsData.EVENT

                            ),
                            PillModelData(
                                event = EventTypeData.PILL,
                                title = "Лисиноприл",
                                dosage = "500 мг, 1 капсула",
                                isCompleted = false,
                                type = CalendarActionsData.EVENT
                            ),
                            BookingModelData(
                                status = BookingStatusData(
                                    type = BookingStatusEntityData.CONFIRMED,
                                    comment = null
                                ),
                                title = "Давыдов Антон Игоревич",
                                time = TimeRangeData(LocalTime.of(12,0), LocalTime.of(13,0)),
                                type = CalendarActionsData.APPOINTMENT
                            ),
                            BookingModelData(
                                status = BookingStatusData(
                                    type = BookingStatusEntityData.CONFIRMATION,
                                    comment = null
                                ),
                                title = "Швецова Софья Максимовна ",
                                time = TimeRangeData(LocalTime.of(15,0), LocalTime.of(16,0)),
                                type = CalendarActionsData.CONSULTATION
                            ),
                            TaskModelData(
                                event = EventTypeData.ACTION,
                                title = "Тест: Updrs1",
                                route = "testUpdrs1Route",
                                isCompleted = false,
                                type = CalendarActionsData.EVENT
                            ),
                            TaskModelData(
                                event = EventTypeData.ACTION,
                                title = "Упражнение: Нарисовать часы",
                                route = "photo_test",
                                isCompleted = true,
                                type = CalendarActionsData.EVENT

                            ),
                            PillModelData(
                                event = EventTypeData.PILL,
                                title = "Лисиноприл",
                                dosage = "500 мг, 1 капсула",
                                isCompleted = false,
                                type = CalendarActionsData.EVENT
                            )
                        )
                    )
                )
                return if(item.data.date== date){
                    item.data.listEvents.toList()
                }else{
                    listOf<BaseEventData>()
                }
            }
        }
    }

    override fun toItemUiModel(
        date: LocalDate,
        isSelectedDate: Boolean,
        listEvents: List<BaseEventData>
    )= CalendarUiModelData.DateData(
        isSelected = isSelectedDate,
        isToday = date.isEqual(LocalDate.now()),
        date = date,
        listEvents = listEvents
    )


}


package com.example.calendar_impl.data

import com.example.calendar_impl.domain.models.BookingModel
import com.example.calendar_impl.domain.models.CalendarActions
import com.example.calendar_impl.domain.models.CalendarData
import com.example.calendar_impl.domain.models.DateEvents
import com.example.calendar_impl.domain.models.ActionModel
import com.example.calendar_impl.domain.models.AvailableTime
import com.example.calendar_impl.domain.models.BookingStatus
import com.example.calendar_impl.domain.models.BookingStatusEntity
import com.example.calendar_impl.domain.models.EventType
import com.example.calendar_impl.domain.models.TimeRange
import com.example.calendar_impl.domain.repositories.CalendarEventsRepository
import ru.fefu.photo_tests_impl.navigation.PhotoTestsImpl
import ru.fefu.written_test_impl.navigation.WrittenTestImpl
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

class CalendarEventsRepositoryImpl@Inject constructor():CalendarEventsRepository {
    override fun getEvents(type: CalendarActions): CalendarData {
        when (type) {
            CalendarActions.APPOINTMENT -> {
                return CalendarData(
                    type = CalendarActions.APPOINTMENT,
                    data = DateEvents(
                        date = LocalDate.of(2023, 11, 3),
                        listEvents = listOf(
                            BookingModel(
                                status = BookingStatus(
                                    type = BookingStatusEntity.CONFIRMATION,
                                    comment = null
                                ),
                                title = "Давыдов Антон Игоревич",
                                time = TimeRange(LocalTime.of(12,0),LocalTime.of(13,0)),
                                type = CalendarActions.APPOINTMENT
                            )
                        )
                    )
                )
            }

            CalendarActions.CONSULTATION -> {
                return CalendarData(
                    type = CalendarActions.APPOINTMENT,
                    data = DateEvents(
                        date = LocalDate.of(2023, 11, 10),
                        listEvents = listOf(
                            BookingModel(
                                status = BookingStatus(
                                    type = BookingStatusEntity.CONFIRMED,
                                    comment = null
                                ),
                                title = "Швецова Софья Максимовна ",
                                time = TimeRange(LocalTime.of(15,0),LocalTime.of(16,0)),
                                type = CalendarActions.CONSULTATION
                            )
                        )
                    )
                )
            }

            CalendarActions.EVENT -> {
                return CalendarData(
                    type = CalendarActions.EVENT,
                    data = DateEvents(
                        date = LocalDate.of(2023, 11, 5),
                        listEvents = listOf(
                            ActionModel(
                                event = EventType.ACTION,
                                title = "Тест: Updrs1",
                                option = WrittenTestImpl().testUpdrs1Route,
                                isCompleted = false,
                                type = CalendarActions.EVENT
                            ),
                            ActionModel(
                                event = EventType.ACTION,
                                title = "Упражнение: Нарисовать часы",
                                option = PhotoTestsImpl().route,
                                isCompleted = true,
                                type = CalendarActions.EVENT

                            ),
                            ActionModel(
                                event = EventType.PILL,
                                title = "Лисиноприл",
                                option = "500 мг, 1 капсула",
                                isCompleted = false,
                                type = CalendarActions.EVENT
                            )
                        )
                    )
                )


            }

            CalendarActions.ALL -> {
                return CalendarData(
                    type = CalendarActions.ALL,
                    data = DateEvents(
                        date = LocalDate.of(2023, 11, 3),
                        listEvents = listOf(
                            BookingModel(
                                status = BookingStatus(
                                    type = BookingStatusEntity.REJECTED,
                                    comment = "Предлагаю 15:00 - 16:00"
                                ),
                                title = "Давыдов Антон Игоревич",
                                time = TimeRange(LocalTime.of(12,0),LocalTime.of(13,0)),
                                type = CalendarActions.APPOINTMENT
                            ),
                            BookingModel(
                                status = BookingStatus(
                                    type = BookingStatusEntity.CONFIRMATION,
                                    comment = null
                                ),
                                title = "Швецова Софья Максимовна ",
                                time = TimeRange(LocalTime.of(15,0),LocalTime.of(16,0)),
                                type = CalendarActions.CONSULTATION
                            ),
                            ActionModel(
                                event = EventType.ACTION,
                                title = "Фото тест",
                                option = "",
                                isCompleted = false,
                                type = CalendarActions.EVENT
                            ),
                            ActionModel(
                                event = EventType.ACTION,
                                title = "Упражнение тест",
                                option = "",
                                isCompleted = true,
                                type = CalendarActions.EVENT

                            ),
                            ActionModel(
                                event = EventType.PILL,
                                title = "Лисиноприл",
                                option = "500 мг, 1 капсула",
                                isCompleted = false,
                                type = CalendarActions.EVENT
                            ),
                            BookingModel(
                                status = BookingStatus(
                                    type = BookingStatusEntity.CONFIRMED,
                                    comment = null
                                ),
                                title = "Давыдов Антон Игоревич",
                                time = TimeRange(LocalTime.of(12,0),LocalTime.of(13,0)),
                                type = CalendarActions.APPOINTMENT
                            ),
                            BookingModel(
                                status = BookingStatus(
                                    type = BookingStatusEntity.CONFIRMATION,
                                    comment = null
                                ),
                                title = "Швецова Софья Максимовна ",
                                time = TimeRange(LocalTime.of(15,0),LocalTime.of(16,0)),
                                type = CalendarActions.CONSULTATION
                            ),
                            ActionModel(
                                event = EventType.ACTION,
                                title = "Фото тест",
                                option = "",
                                isCompleted = false,
                                type = CalendarActions.EVENT
                            ),
                            ActionModel(
                                event = EventType.ACTION,
                                title = "Упражнение тест",
                                option = "",
                                isCompleted = true,
                                type = CalendarActions.EVENT

                            ),
                            ActionModel(
                                event = EventType.PILL,
                                title = "Лисиноприл",
                                option = "500 мг, 1 капсула",
                                isCompleted = false,
                                type = CalendarActions.EVENT
                            )
                        )
                    )
                )
            }
        }
    }

    override fun getRecords(): List<AvailableTime> {
        return listOf(
            AvailableTime(
                date =LocalDate.of(2023, 11, 5),
                timeRange = TimeRange(
                    LocalTime.of(12,0),
                    LocalTime.of(14,0),
                ),
                doctorsFullName = "Криспин Анатолий Алексеевич"
            ),
            AvailableTime(
                date =LocalDate.of(2023, 11, 5),
                timeRange = TimeRange(
                    LocalTime.of(16,0),
                    LocalTime.of(17,0),
                ),
                doctorsFullName = "Криспин Анатолий Алексеевич"
            )
        )
    }
}


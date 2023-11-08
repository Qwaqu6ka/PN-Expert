package ru.fefu.data.calendar.repositoty

import ru.fefu.data.calendar.models.AvailableTimeData
import ru.fefu.data.calendar.models.CalendarActionsData
import ru.fefu.data.calendar.models.CalendarUiModelData
import ru.fefu.data.calendar.models.TimeRangeData
import ru.fefu.data.calendar.sources.CalendarDataSourceI
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

class CalendarEventsDataRepositoryImpl@Inject constructor(
    private val dataSource: CalendarDataSourceI
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
        return  dataSource.getData(startDate,lastSelectedDate,typeCalendar)
    }


}


package ru.fefu.data.calendar.models

data class BookingModelData(
    val status: BookingStatusData,
    val title:String,//ФИО либо Название
    val time: TimeRangeData,
    override val type: CalendarActionsData,
): BaseEventData

data class BookingStatusData(
    val type: BookingStatusEntityData,
    val comment:String?
)

enum class BookingStatusEntityData{
    CONFIRMATION,CONFIRMED,REJECTED
}


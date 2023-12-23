package ru.fefu.calendar_impl.domain.models

data class BookingModel(
    val status: BookingStatus,
    val title:String,//ФИО либо Название
    val time: TimeRange,
    override val type: CalendarActions,
): BaseEvent

data class BookingStatus(
    val type: BookingStatusEntity,
    val comment:String?
)

enum class BookingStatusEntity{
    CONFIRMATION,CONFIRMED,REJECTED
}


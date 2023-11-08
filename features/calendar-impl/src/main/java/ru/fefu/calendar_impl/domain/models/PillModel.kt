package ru.fefu.calendar_impl.domain.models

data class PillModel(
    val event: EventType,
    val title:String,
    val dosage:String, //dosage | route
    val isCompleted:Boolean,
    override val type: CalendarActions
): BaseEvent
package ru.fefu.data.calendar.models

data class PillModelData(
    val event: EventTypeData,
    val title:String,
    val dosage:String, //dosage | route
    val isCompleted:Boolean,
    override val type: CalendarActionsData
): BaseEventData
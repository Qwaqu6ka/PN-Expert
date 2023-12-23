package ru.fefu.data.calendar.models

data class TaskModelData(
    val event: EventTypeData,
    val title:String,
    val route:String, //dosage | route
    val isCompleted:Boolean,
    override val type: CalendarActionsData
): BaseEventData

enum class EventTypeData{
    ACTION,PILL
}

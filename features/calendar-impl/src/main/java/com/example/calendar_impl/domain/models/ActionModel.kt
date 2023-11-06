package com.example.calendar_impl.domain.models

data class ActionModel(
    val event:EventType,
    val title:String,
    val option:String, //dosage | route
    val isCompleted:Boolean,
    override val type:CalendarActions
):BaseEvent

enum class EventType{
    ACTION,PILL
}

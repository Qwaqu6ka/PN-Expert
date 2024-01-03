package ru.fefu.calendar_impl.domain.models

data class TaskModel(
    val event: EventType,
    val title: String,
    val route: TestModel,
    val isCompleted: Boolean,
    override val type: CalendarActions
) : BaseEvent

enum class EventType {
    ACTION, PILL
}

package ru.fefu.calendar_api

import ru.fefu.feature_api.FeatureApi

interface CalendarApi : FeatureApi {
    val route: String
}
package ru.fefu.main_api

import ru.fefu.feature_api.FeatureApi

interface MainPageApi : FeatureApi {
    val route: String
}
package ru.fefu.photo_test_api

import ru.fefu.feature_api.FeatureApi

interface PhotoTestApi:FeatureApi {
    val route: String
}
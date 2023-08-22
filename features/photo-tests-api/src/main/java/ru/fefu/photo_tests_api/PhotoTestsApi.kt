package ru.fefu.photo_tests_api

import ru.fefu.feature_api.FeatureApi

interface PhotoTestsApi:FeatureApi {
    val route: String
}
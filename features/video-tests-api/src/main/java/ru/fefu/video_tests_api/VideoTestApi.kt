package ru.fefu.video_tests_api

import ru.fefu.feature_api.FeatureApi

interface VideoTestApi : FeatureApi {
    val standUpTestRoute: String
}
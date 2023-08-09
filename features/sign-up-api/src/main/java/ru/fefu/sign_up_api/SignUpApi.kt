package ru.fefu.sign_up_api

import ru.fefu.feature_api.FeatureApi

interface SignUpApi : FeatureApi {
    val route: String
}
package ru.fefu.pnexpert.navigation

import ru.fefu.main_api.MainPageApi
import ru.fefu.sign_up_api.SignUpApi

interface FeatureApiHolder {

    val signUpApi: SignUpApi

    val mainPageApi: MainPageApi
}
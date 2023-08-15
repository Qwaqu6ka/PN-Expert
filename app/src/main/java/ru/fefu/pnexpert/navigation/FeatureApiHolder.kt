package ru.fefu.pnexpert.navigation

import ru.fefu.main_api.MainPageApi
import ru.fefu.sign_up_api.SignUpApi
import ru.fefu.written_test_api.WrittenTestApi

interface FeatureApiHolder {

    val signUpApi: SignUpApi

    val mainPageApi: MainPageApi

    val writtenTestApi: WrittenTestApi
}
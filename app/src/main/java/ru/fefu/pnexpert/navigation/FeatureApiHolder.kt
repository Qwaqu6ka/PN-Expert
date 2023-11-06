package ru.fefu.pnexpert.navigation

import com.example.calendar_api.CalendarApi
import ru.fefu.main_api.MainPageApi
import ru.fefu.photo_tests_api.PhotoTestsApi
import ru.fefu.sign_up_api.SignUpApi
import ru.fefu.written_test_api.WrittenTestApi

interface FeatureApiHolder {

    val signUpApi: SignUpApi

    val mainPageApi: MainPageApi

    val writtenTestApi: WrittenTestApi

    val photoTestsApi: PhotoTestsApi

    val calendarApi: CalendarApi
}
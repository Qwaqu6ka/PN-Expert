package ru.fefu.written_test_api

import ru.fefu.feature_api.FeatureApi

interface WrittenTestApi : FeatureApi {
    val testUpdrs1Route: String
    val testUpdrs2Route: String
    val testUpdrs3Route: String
    val testUpdrs4Route: String
    val testPdq39Route: String
    val testHadsRoute: String
    val testSchwabEnglandRoute: String
    val testHoehnYahrRoute: String
    val testFabRoute: String
    val testPsqiRoute: String
}
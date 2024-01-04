package ru.fefu.video_tests_api

import ru.fefu.feature_api.FeatureApi

interface VideoTestApi : FeatureApi {
    val facialExpressivenessTestRoute: String
    val fingersTappingTestRoute: String
    val brushMovementsTestRoute: String
    val brushPronationSupinationTestRoute: String
    val footTappingTestRoute: String
    val legsMobilityTestRoute: String
    val gettingUpFromChairTestRoute: String
    val gaitTestRoute: String
    val posturalHandsTremorTestRoute: String
    val kineticHandsTremorTestRoute: String
    val amplitudeOfRestingTremorTestRoute: String
}
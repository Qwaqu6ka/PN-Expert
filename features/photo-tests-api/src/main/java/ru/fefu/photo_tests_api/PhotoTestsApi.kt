package ru.fefu.photo_tests_api

import ru.fefu.feature_api.FeatureApi

interface PhotoTestsApi:FeatureApi {
    val clockPhotoTestRoute: String
    val facePhotoTestRoute: String
    val fullLengthPhotoTestRoute: String
    val handwritingPhotoTestRoute: String
}
package ru.fefu.photo_tests_impl.domain.models

sealed class PhotoTestType {
    object ClockPhotoTest : PhotoTestType()

    object FacePhotoTest : PhotoTestType()

    object FullLengthPhotoTest : PhotoTestType()

    object HandwritingPhotoTest: PhotoTestType()
}
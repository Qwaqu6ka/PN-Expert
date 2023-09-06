package ru.fefu.photo_tests_impl.presentation.models

import ru.fefu.photo_tests_impl.domain.models.PhotoTestDataModel

data class TestDataState(
    val isLoading:Boolean =false,
    val data: PhotoTestDataModel? = null,
    val error:String? = null
)

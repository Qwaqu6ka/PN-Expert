package ru.fefu.photo_tests_impl.presentation.models

import ru.fefu.photo_tests_impl.domain.models.PhotoTestGuide

data class TestDataState(
    val isLoading:Boolean =false,
    val data: PhotoTestGuide? = null,
    val error:String? = null
)

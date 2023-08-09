package ru.fefu.sign_up_impl.utils.models

data class ValidationResult(
    val success: Boolean,
    val errorMessage:String? = null
)

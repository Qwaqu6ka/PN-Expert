package ru.fefu.pnexpert.utils.validation.models

data class ValidationResult(
    val success: Boolean,
    val errorMessage:String? = null
)

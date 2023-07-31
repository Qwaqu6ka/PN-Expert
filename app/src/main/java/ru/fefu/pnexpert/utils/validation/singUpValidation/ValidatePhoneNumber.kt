package ru.fefu.pnexpert.utils.validation.singUpValidation

import ru.fefu.pnexpert.utils.validation.models.ValidationResult
import javax.inject.Inject


class ValidatePhoneNumber @Inject constructor(){
    operator fun invoke(phoneNumber: String): ValidationResult {
        if (phoneNumber.isBlank()) {
            return ValidationResult(
                success = false,
                errorMessage = "Поле не должно быть пустым"
            )
        }
        if(phoneNumber.length != 12){
            return ValidationResult(
                success = false,
                errorMessage = "Номер телефона должен состоять из 10  символов и кода страны"
            )
        }
        return ValidationResult(
            success = true
        )
    }
}
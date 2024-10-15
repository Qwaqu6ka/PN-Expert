package ru.fefu.sign_up_impl.utils.singUpValidation

import ru.fefu.sign_up_impl.utils.models.ValidationResult

class PhoneNumberValidator {
    operator fun invoke(phoneNumber: String): ValidationResult {
        if (phoneNumber.isBlank()) {
            return ValidationResult(
                success = false,
                errorMessage = "Поле не должно быть пустым"
            )
        }
        if (phoneNumber.length != 12) {
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
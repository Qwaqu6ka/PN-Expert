package ru.fefu.pnexpert.utils.validation.singUpValidation

import ru.fefu.pnexpert.utils.validation.models.ValidationResult
import javax.inject.Inject

class ValidatePhoneNumber {
    operator fun invoke(phoneNumber: String): ValidationResult {
        if (phoneNumber.isBlank()) {
            return ValidationResult(
                success = false,
                errorMessage = "Поле не должно быть пустым"
            )
        }
        if(!(phoneNumber.length == 12 && phoneNumber[0] == '+')&&(phoneNumber.length != 11)){
            return ValidationResult(
                success = false,
                errorMessage = "Номер телефона должен состоять из 11 символов, не учитывая символ '+'"
            )
        }
        return ValidationResult(
            success = true
        )
    }
}
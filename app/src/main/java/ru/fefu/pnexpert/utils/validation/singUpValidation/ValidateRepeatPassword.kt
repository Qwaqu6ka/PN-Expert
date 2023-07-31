package ru.fefu.pnexpert.utils.validation.singUpValidation

import ru.fefu.pnexpert.utils.validation.models.ValidationResult
import javax.inject.Inject

class ValidateRepeatPassword @Inject constructor(){
    operator fun invoke(password: String, repeatPassword: String): ValidationResult {
        if (repeatPassword.isBlank()) {
            return ValidationResult(
                success = false,
                errorMessage = "Поле не должно быть пустым"
            )
        }
        if (password != repeatPassword){
            return ValidationResult(
                success = false,
                errorMessage = "Пароли не совпадают"
            )
        }
        return ValidationResult(
            success = true
        )
    }
}
package ru.fefu.sign_up_impl.utils.singUpValidation

import ru.fefu.sign_up_impl.utils.models.ValidationResult
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
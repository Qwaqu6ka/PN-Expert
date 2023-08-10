package ru.fefu.sign_up_impl.utils.models

data class SingUpFormState(
    val phoneNumber: String = "",
    val phoneNumberError: String? = null,
    val password:String = "",
    val passwordError:String? = null,
    val repeatPassword:String = "",
    val repeatPasswordError:String? = null,
)

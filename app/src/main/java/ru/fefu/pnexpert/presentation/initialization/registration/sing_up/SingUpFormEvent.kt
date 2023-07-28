package ru.fefu.pnexpert.presentation.initialization.registration.sing_up

sealed class SingUpFormEvent{
    data class PhoneNumberChanged(val phoneNumber:String):SingUpFormEvent()
    data class PasswordChanged(val password:String):SingUpFormEvent()
    data class RepeatPasswordChanged(val repeatPassword:String):SingUpFormEvent()
    object Submit:SingUpFormEvent()
}
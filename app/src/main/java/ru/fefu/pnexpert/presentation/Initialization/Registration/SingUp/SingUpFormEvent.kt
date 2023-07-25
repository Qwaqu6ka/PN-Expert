package ru.fefu.pnexpert.presentation.Initialization.Registration.SingUp

sealed class SingUpFormEvent{
    data class PhoneNumberChanged(val phoneNumber:String):SingUpFormEvent()
    data class PasswordChanged(val password:String):SingUpFormEvent()
    data class RepeatPasswordChanged(val repeatPassword:String):SingUpFormEvent()
    object Submit:SingUpFormEvent()
}
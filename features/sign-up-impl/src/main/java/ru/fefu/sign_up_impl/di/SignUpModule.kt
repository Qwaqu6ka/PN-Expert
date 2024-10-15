package ru.fefu.sign_up_impl.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.fefu.sign_up_api.SignUpApi
import ru.fefu.sign_up_impl.navigation.SingUpImpl
import ru.fefu.sign_up_impl.utils.singUpValidation.PasswordValidator
import ru.fefu.sign_up_impl.utils.singUpValidation.PhoneNumberValidator
import ru.fefu.sign_up_impl.utils.singUpValidation.RepeatPasswordValidator

val signUpModule = module {
    single<SignUpApi> {
        SingUpImpl(get())
    }

    singleOf(::PasswordValidator)
    singleOf(::PhoneNumberValidator)
    singleOf(::RepeatPasswordValidator)
}
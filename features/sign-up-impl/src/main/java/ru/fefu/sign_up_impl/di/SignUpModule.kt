package ru.fefu.sign_up_impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.fefu.sign_up_api.SignUpApi
import ru.fefu.sign_up_impl.navigation.SingUpImpl

@Module
@InstallIn(SingletonComponent::class)
interface SignUpModule {
    @Binds
    fun bindSingUp(singUpImpl: SingUpImpl): SignUpApi
}
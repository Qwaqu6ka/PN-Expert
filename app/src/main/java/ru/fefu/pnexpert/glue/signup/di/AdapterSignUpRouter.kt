package ru.fefu.pnexpert.glue.signup.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.fefu.pnexpert.presentation.BottomTabs
import ru.fefu.sign_up_impl.navigation.SignUpRouter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterSignUpRouter {

    @Provides
    @Singleton
    fun provideSignUpRouter() = object : SignUpRouter {
        override fun provideMainTabRoute() = BottomTabs.Main.route
    }
}
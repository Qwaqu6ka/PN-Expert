package ru.fefu.main_impl.di

import ru.fefu.main_api.MainPageApi
import ru.fefu.main_impl.navigation.MainPageImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface MainPageModule {
    @Binds
    fun bindMainPage(mainImpl: MainPageImpl): MainPageApi
}
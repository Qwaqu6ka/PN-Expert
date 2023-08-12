package com.example.main_impl.di

import com.example.main_api.MainPageApi
import com.example.main_impl.navigation.MainPageImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface MainPageModule {
    @Binds
    fun bindSingUp(singUpImpl: MainPageImpl): MainPageApi
}
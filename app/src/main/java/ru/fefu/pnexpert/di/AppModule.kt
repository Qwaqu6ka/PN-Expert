package ru.fefu.pnexpert.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import ru.fefu.pnexpert.presentation.ActivityScopeViewModel

val appModule = module {
    viewModelOf(::ActivityScopeViewModel)
}
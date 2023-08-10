package ru.fefu.pnexpert

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Singleton


@Singleton
@HiltAndroidApp
class App : Application()

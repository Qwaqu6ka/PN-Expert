package ru.fefu.pnexpert.main.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ru.fefu.pnexpert.R

sealed class BottomNavTab(
    val route: String,
    @StringRes val titleRes: Int,
    @DrawableRes val iconRes: Int
) {
    object Main : BottomNavTab("MainTab", R.string.main_page, R.drawable.ic_heart)
    object Profile : BottomNavTab("ProfileTab", R.string.profile, R.drawable.ic_person)
    object Services : BottomNavTab("ServicesTab", R.string.services, R.drawable.ic_settings)
    object History : BottomNavTab("HistoryTab", R.string.history, R.drawable.ic_notebook)
}

sealed class MarkedBottomNabTab(
    route: String,
    @StringRes titleRes: Int,
    @DrawableRes iconRes: Int,
    val isMarked: Boolean = false
) : BottomNavTab(route, titleRes, iconRes) {
    object Notes : MarkedBottomNabTab("NotesTab", R.string.note, R.drawable.ic_calendar)
}

/**
 * Place tab here in order which you want to they placed in app
 */
val bottomNavTabItems = listOf(
    BottomNavTab.Services,
    MarkedBottomNabTab.Notes,
    BottomNavTab.Main,
    BottomNavTab.History,
    BottomNavTab.Profile
)
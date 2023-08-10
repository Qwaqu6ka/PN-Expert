package ru.fefu.pnexpert.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ru.fefu.pnexpert.R

enum class BottomTabs(
    val route: String,
    @StringRes val titleRes: Int,
    @DrawableRes val iconRes: Int
) {
    Services("ServicesTab", R.string.services, R.drawable.ic_settings),
    Notes("NotesTab", R.string.note, R.drawable.ic_calendar),
    Main("MainTab", R.string.main, R.drawable.ic_heart),
    History("HistoryTab", R.string.history, R.drawable.ic_notebook),
    Profile("ProfileTab", R.string.profile, R.drawable.ic_person)
}
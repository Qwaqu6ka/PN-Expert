package ru.fefu.pnexpert.main.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.fefu.pnexpert.R
import ru.fefu.pnexpert.main.presentation.components.Toolbar

@Composable
fun MainTab() {
    Toolbar(title = stringResource(id = R.string.events))
}
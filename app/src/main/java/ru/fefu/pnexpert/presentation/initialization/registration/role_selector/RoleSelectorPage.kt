package ru.fefu.pnexpert.presentation.initialization.registration.role_selector

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.fefu.pnexpert.R
import ru.fefu.pnexpert.presentation.theme.PnExpertTheme

@Composable
fun RoleSelectorPage() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        RoleImage()
    }
}

@Composable
fun RoleImage() {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16f/9f)
            .clip(PnExpertTheme.shapes.mainShapes.),
        painter = painterResource(id = R.drawable.select_role_img),
        contentDescription = "selectRoleImg",
        contentScale = ContentScale.FillWidth
    )
}


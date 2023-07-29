package ru.fefu.pnexpert.presentation.initialization.registration.role_selector

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.fefu.pnexpert.R
import ru.fefu.pnexpert.presentation.theme.PnExpertTheme

@Composable
fun RoleSelectorPage() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        RoleImage()
        Spacer(modifier = Modifier.height(24.dp))
        RoleSelectors()
    }
}

@Composable
private fun RoleCard() {
    val fieldShadow = 6.dp

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(fieldShadow, PnExpertTheme.shapes.mainShapes.appDefault10),
        colors = CardDefaults.cardColors(
            containerColor = PnExpertTheme.colors.mainAppColors.AppWhiteColor
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.person_role_icon),
                contentDescription = "roleImage"
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "Роль",
                style = PnExpertTheme.typography.text.medium_14,
                color = PnExpertTheme.colors.textColors.FontDarkColor
            )
            Spacer(modifier = Modifier.fillMaxWidth(0.9f))
            Checkbox(
                modifier = Modifier,
                checked = false,
                onCheckedChange = {}
            )
        }
    }
}

@Composable
private fun RoleSelectors() {

    val roleCount = 4

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        for (idx in 0 until roleCount) {
            RoleCard()
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun RoleImage() {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f)
            .clip(PnExpertTheme.shapes.imageShapes.imageClassic15),
        painter = painterResource(id = R.drawable.select_role_img),
        contentDescription = "selectRoleImg",
        contentScale = ContentScale.FillWidth
    )
}


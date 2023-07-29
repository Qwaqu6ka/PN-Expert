package ru.fefu.pnexpert.presentation.initialization.registration.role_selector

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
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
        Spacer(modifier = Modifier.height(70.dp))
        NextButton()
    }
}

@Composable
private fun NextButton() {
    TextButton(
        modifier = Modifier
            .fillMaxWidth()
            .height(PnExpertTheme.sizes.buttonSize.buttonClassic55),
        onClick = {},
        shape = PnExpertTheme.shapes.buttonShapes.buttonClassic10,
        colors = ButtonDefaults.textButtonColors(
            containerColor = PnExpertTheme.colors.buttonColors.ButtonNormalBlueColor
        )
    ) {
        Text(
            text = "Подтвердить",
            style = PnExpertTheme.typography.subtitle.medium_18,
            color = PnExpertTheme.colors.textColors.FontWhiteColor
        )
    }
}

@Composable
private fun RoleCard() {
    val fieldShadow = 6.dp

    var isClicked by remember { mutableStateOf(false) }
    var cardHeight by remember { mutableStateOf(0) }
    val interactionSource = remember { MutableInteractionSource() }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(fieldShadow, PnExpertTheme.shapes.mainShapes.appDefault10)
            .onGloballyPositioned {
                cardHeight = it.size.height
            }
            .clickable(
                interactionSource = interactionSource,
                indication = rememberRipple(radius = 400.dp),
                onClick = {
                    isClicked = !isClicked
                },
            ),
        shape = PnExpertTheme.shapes.mainShapes.appDefault10,
        colors = CardDefaults.cardColors(
            containerColor = PnExpertTheme.colors.mainAppColors.AppWhiteColor
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(PnExpertTheme.sizes.buttonSize.buttonClassic55)
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column() {
                Icon(
                    painter = painterResource(id = R.drawable.person_role_icon),
                    contentDescription = "roleImage"
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column() {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Роль",
                        style = PnExpertTheme.typography.text.medium_14,
                        color = PnExpertTheme.colors.textColors.FontDarkColor
                    )
                    Spacer(modifier = Modifier.fillMaxWidth(0.9f))
                    Checkbox(
                        modifier = Modifier,
                        checked = false,
                        onCheckedChange = {},
                        colors = CheckboxDefaults.colors(

                        )
                    )
                }
            }
        }
        AnimatedVisibility(visible = isClicked) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = (24.dp + 40.dp), end = 12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(PnExpertTheme.colors.textColors.FontGreyColor)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding( end = 10.dp),
                    text = "Введите номер телефона и пин код чтобы войти в аккаут код чтобы войти в аккаут Введите номер телефона и пин код чтобы войти в аккаут код чтобы войти в аккаут",
                    style = PnExpertTheme.typography.text.regular_14,
                    color = PnExpertTheme.colors.textColors.FontGreyColor
                )
                Spacer(modifier = Modifier.height(22.dp))
            }
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


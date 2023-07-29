package ru.fefu.pnexpert.presentation.initialization.registration.role_selector

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.fefu.pnexpert.R
import ru.fefu.pnexpert.presentation.initialization.registration.RegistrationViewModel
import ru.fefu.pnexpert.presentation.theme.PnExpertTheme

data class Role(
    val roleName:String,
    val roleDescriptor: String,
)

@Composable
fun RoleSelectorScreen(
    viewModel: RegistrationViewModel
) {
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
private fun RoleCard(
    role: Role,
    selectedRole: MutableState<String>
) {
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
                .padding(start = 12.dp),
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
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = role.roleName,
                        style = PnExpertTheme.typography.text.medium_14,
                        color = PnExpertTheme.colors.textColors.FontDarkColor
                    )
                    RadioButton(
                        selected = selectedRole.value == role.roleName,
                        onClick = {
                            selectedRole.value = role.roleName
                        },
                        modifier = Modifier,
                        colors = RadioButtonDefaults.colors(
                            selectedColor = PnExpertTheme.colors.mainAppColors.AppBlueColor,
                            unselectedColor = PnExpertTheme.colors.textColors.FontGreyColor
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
                        .padding(end = 10.dp),
                    text = role.roleDescriptor,
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

    val roles = listOf(
        Role("Пациент", "Введите номер телефона и пин код чтобы войти в аккаут код чтобы войти в аккаут Введите номер телефона и пин код чтобы войти в аккаут код чтобы войти в аккаут"),
        Role("Родственник / Опекун", "Введите номер телефона и пин код чтобы войти в аккаут код чтобы войти в аккаут Введите номер телефона и пин код чтобы войти в аккаут код чтобы войти в аккаут"),
        Role("Специалист", "Введите номер телефона и пин код чтобы войти в аккаут код чтобы войти в аккаут Введите номер телефона и пин код чтобы войти в аккаут код чтобы войти в аккаут"),
        Role("Администратор / Менеджер", "Введите номер телефона и пин код чтобы войти в аккаут код чтобы войти в аккаут Введите номер телефона и пин код чтобы войти в аккаут код чтобы войти в аккаут")
    )

    val selectedRole = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        for (idx in roles.indices) {
            RoleCard(roles[idx], selectedRole)
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


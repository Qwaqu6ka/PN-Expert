package ru.fefu.photo_tests_impl.presentation.components

import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.fefu.theme.PnExpertTheme

@Composable
internal fun Photo(
    photoUri: Uri,
    modifier: Modifier = Modifier,
    @DrawableRes placeholderResId: Int? = null
) {
    val model = remember(photoUri) {
        if (photoUri == Uri.EMPTY) {
            placeholderResId
        } else {
            photoUri
        }
    }

    Surface(
        modifier = modifier,
        shape = PnExpertTheme.shapes.imageShapes.imageClassic15,
        border = BorderStroke(1.dp, PnExpertTheme.colors.mainAppColors.AppBlueColor)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(model).build(),
            contentDescription = null,
        )
    }
}
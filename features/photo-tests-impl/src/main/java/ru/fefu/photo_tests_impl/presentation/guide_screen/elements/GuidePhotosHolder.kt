package ru.fefu.photo_tests_impl.presentation.guide_screen.elements

import android.provider.ContactsContract.Contacts.Photo
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.fefu.photo_test_impl.R
import ru.fefu.photo_tests_impl.domain.models.PhotoTestTask
import ru.fefu.photo_tests_impl.domain.models.TestPhoto
import ru.fefu.theme.PnExpertTheme

@Composable
internal fun GuidePhotosHolder(
    modifier: Modifier,
    guidePhotos: List<TestPhoto>,
) {
    Column(
        modifier = modifier
    ) {
        for ((id,photo) in guidePhotos.withIndex()){
            GuidPhoto(photo = photo)
            if (guidePhotos.size > 1 && id != guidePhotos.size-1)
                Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
private fun GuidPhoto(
    modifier: Modifier = Modifier,
    photo: TestPhoto
){
    PnExpertTheme {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .height(200.dp)
                .shadow(6.dp, PnExpertTheme.shapes.imageShapes.imageClassic15),
            shape = PnExpertTheme.shapes.imageShapes.imageClassic15
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(PnExpertTheme.shapes.imageShapes.imageClassic15),
                painter = painterResource(id = photo.drawableId),
                contentScale = ContentScale.FillWidth,
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
private fun PreviewGuidPhoto(){
    PnExpertTheme {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .shadow(6.dp, PnExpertTheme.shapes.imageShapes.imageClassic15),
            shape = PnExpertTheme.shapes.imageShapes.imageClassic15
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(PnExpertTheme.shapes.imageShapes.imageClassic15),
                painter = painterResource(id = R.drawable.photo_test_clock),
                contentScale = ContentScale.FillWidth,
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
private fun PreviewGuidePhotosHolder() {

    val guidePhotos = listOf<TestPhoto>(
        TestPhoto(R.drawable.photo_test_clock),
        TestPhoto(R.drawable.photo_test_clock)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        for ((id,photo) in guidePhotos.withIndex()){
            GuidPhoto(photo = photo)
            if (guidePhotos.size > 1 && id != guidePhotos.size-1)
                Spacer(modifier = Modifier.height(12.dp))
        }
    }
}
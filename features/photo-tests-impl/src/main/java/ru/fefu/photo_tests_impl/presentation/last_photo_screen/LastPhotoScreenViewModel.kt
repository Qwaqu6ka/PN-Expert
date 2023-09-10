package ru.fefu.photo_tests_impl.presentation.last_photo_screen

import android.net.Uri
import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject


class LastPhotoScreenViewModel@AssistedInject constructor(
    @Assisted private val photoPath:Uri,
):ViewModel() {
    fun getPhotoPath():Uri{
        return photoPath
    }

    @AssistedFactory
    interface Factory {
        fun create(photoPath: Uri): LastPhotoScreenViewModel
    }
}
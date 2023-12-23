package ru.fefu.video_tests_impl.presentation;

import androidx.lifecycle.SavedStateHandle;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class VideoTestViewModel_Factory implements Factory<VideoTestViewModel> {
  private final Provider<SavedStateHandle> savedStateHandleProvider;

  public VideoTestViewModel_Factory(Provider<SavedStateHandle> savedStateHandleProvider) {
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public VideoTestViewModel get() {
    return newInstance(savedStateHandleProvider.get());
  }

  public static VideoTestViewModel_Factory create(
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new VideoTestViewModel_Factory(savedStateHandleProvider);
  }

  public static VideoTestViewModel newInstance(SavedStateHandle savedStateHandle) {
    return new VideoTestViewModel(savedStateHandle);
  }
}

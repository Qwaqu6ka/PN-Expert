package ru.fefu.video_tests_impl.presentation;

import android.app.Application;
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
  private final Provider<Application> applicationProvider;

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  public VideoTestViewModel_Factory(Provider<Application> applicationProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    this.applicationProvider = applicationProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public VideoTestViewModel get() {
    return newInstance(applicationProvider.get(), savedStateHandleProvider.get());
  }

  public static VideoTestViewModel_Factory create(Provider<Application> applicationProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new VideoTestViewModel_Factory(applicationProvider, savedStateHandleProvider);
  }

  public static VideoTestViewModel newInstance(Application application,
      SavedStateHandle savedStateHandle) {
    return new VideoTestViewModel(application, savedStateHandle);
  }
}

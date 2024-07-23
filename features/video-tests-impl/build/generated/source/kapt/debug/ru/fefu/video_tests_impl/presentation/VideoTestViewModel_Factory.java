package ru.fefu.video_tests_impl.presentation;

import android.app.Application;
import androidx.lifecycle.SavedStateHandle;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import ru.fefu.video_tests_impl.domain.CameraXRepository;

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

  private final Provider<CameraXRepository> cameraXRepositoryProvider;

  public VideoTestViewModel_Factory(Provider<Application> applicationProvider,
      Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<CameraXRepository> cameraXRepositoryProvider) {
    this.applicationProvider = applicationProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
    this.cameraXRepositoryProvider = cameraXRepositoryProvider;
  }

  @Override
  public VideoTestViewModel get() {
    return newInstance(applicationProvider.get(), savedStateHandleProvider.get(), cameraXRepositoryProvider.get());
  }

  public static VideoTestViewModel_Factory create(Provider<Application> applicationProvider,
      Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<CameraXRepository> cameraXRepositoryProvider) {
    return new VideoTestViewModel_Factory(applicationProvider, savedStateHandleProvider, cameraXRepositoryProvider);
  }

  public static VideoTestViewModel newInstance(Application application,
      SavedStateHandle savedStateHandle, CameraXRepository cameraXRepository) {
    return new VideoTestViewModel(application, savedStateHandle, cameraXRepository);
  }
}

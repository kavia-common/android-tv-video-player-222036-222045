# Android TV Video Player - Test Execution Results

Run: ./gradlew :app:connectedDebugAndroidTest --no-daemon --stacktrace
Timestamp (epoch): 1762796140

Summary
- Total tests executed: 0
- Passed: 0
- Failed: 0
- Skipped: 0
- Total execution time: N/A (no device)

Outcome
The instrumentation test task failed to run because no Android device/emulator was connected. Gradle error:
com.android.builder.testing.api.DeviceException: No connected devices!
Task: :app:connectedDebugAndroidTest

Per-test listing (not executed due to no device)
- BrowseAndPlayerInstrumentedTest#browse_rendersMainContainer_andFocusIsSet — SKIPPED (no device)
- BrowseAndPlayerInstrumentedTest#browse_clicksFirstItem_andLaunchesPlayerActivity — SKIPPED (no device)
- BrowseAndPlayerInstrumentedTest#player_showsPlayerView_andHandlesDpadKeys — SKIPPED (no device)
- PlayerFragmentLifecycleInstrumentedTest#playerFragment_transitions_doNotCrash — SKIPPED (no device)
- PlayerErrorHandlingInstrumentedTest#player_withMissingUrl_handlesBackWithoutCrash — SKIPPED (no device)

Stack trace excerpt
Execution failed for task ':app:connectedDebugAndroidTest'.
> com.android.builder.testing.api.DeviceException: No connected devices!
  at com.android.build.gradle.internal.testing.ConnectedDeviceProvider.init(ConnectedDeviceProvider.kt:...)
  at com.android.build.gradle.internal.testing.ConnectedDeviceProvider.use(ConnectedDeviceProvider.kt:...)
  at com.android.build.gradle.internal.tasks.DeviceProviderInstrumentTestTaskBase.doTaskAction(DeviceProviderInstrumentTestTaskBase.java:...)

How to fix and re-run
1) Start an emulator or connect a physical device and ensure List of devices attached shows it.
   - Example to start emulator in CI: sdkmanager --install "system-images;android-30;google_apis;x86_64" && avdmanager create avd -n test -k "system-images;android-30;google_apis;x86_64" --device tv_1080p && emulator -avd test -no-snapshot -gpu swiftshader_indirect -no-audio -no-window &
   - Wait for boot: adb wait-for-device && adb shell getprop sys.boot_completed
2) Re-run: ./gradlew :app:connectedDebugAndroidTest --no-daemon --stacktrace

Notes
- Unit tests can be run without a device: ./gradlew :app:testDebugUnitTest
- Instrumentation tests require API 26+ emulator; network access is recommended for ExoPlayer initialization.

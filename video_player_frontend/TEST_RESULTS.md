# Android TV Video Player - Test Suite

This document summarizes the tests added and how to run them.

## Test Categories

- Unit tests (host JVM):
  - util/test_VideoCatalogTest.kt
  - ui/test_PlayerFragmentUnitTest.kt (Robolectric runner for non-UI logic)

- Instrumentation tests (on device/emulator):
  - ui/test_BrowseAndPlayerInstrumented.kt
  - ui/test_PlayerFragmentLifecycleInstrumented.kt
  - ui/test_PlayerErrorHandlingInstrumented.kt

## Coverage Highlights

- Browse UI rendering and focusability
- Item click opens PlayerActivity
- PlayerView visibility and DPAD/Media key handling (play/pause/seek/back)
- PlayerFragment lifecycle transitions (initialize/release without crashes)
- Error handling for missing URL extras
- VideoCatalog content availability and URL types

## Running Tests (CI-friendly)

- Unit tests:
  ./gradlew :app:testDebugUnitTest --no-daemon --stacktrace

- Instrumentation tests (require emulator/device connected):
  ./gradlew :app:connectedDebugAndroidTest --no-daemon --stacktrace

Note: Ensure an Android emulator (API 26+) is running in CI for instrumentation tests. ExoPlayer performs real preparation; network must be reachable, but the tests avoid strict playback assertions to remain robust.

## Notes

- No backend is required; tests use static catalog data.
- Tests avoid modifying app code and rely on public behaviors and Intent contracts.

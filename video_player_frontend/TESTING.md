# Testing Guide

- Unit tests (host JVM): uses JUnit4 and Robolectric for Android SDK stubs where needed.
  Run: ./gradlew :app:testDebugUnitTest

- Instrumentation tests (device/emulator): uses Espresso and AndroidX test runner.
  Run: ./gradlew :app:connectedDebugAndroidTest

Robolectric configuration is set in src/test/resources/robolectric.properties (sdk=28) to ensure consistent environment. No network or backend is required.

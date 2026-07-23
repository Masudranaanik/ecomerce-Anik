# Implementation Plan - Firebase Auth Hang Fix

Fixing the infinite loading issue during Sign Up and Login by adding missing dependencies and improving error handling.

## User Review Required

> [!IMPORTANT]
> - **Dependency Fix**: I need to add `kotlinx-coroutines-play-services`. Without this, Firebase's asynchronous tasks (like login/signup) don't play well with Kotlin Coroutines, causing the "infinite spinning" you see.
> - **Error Handling**: I will wrap the auth calls in more robust error-catching blocks to ensure the loading spinner always stops, even if something goes wrong.

## Proposed Changes

### 1. Build Configuration

#### [MODIFY] [libs.versions.toml](file:///C:/Users/lab%20506-12/OneDrive/Desktop/Exam/ecomerceAnik/gradle/libs.versions.toml)
- Add `kotlinx-coroutines-play-services` library.

#### [MODIFY] [build.gradle.kts (App)](file:///C:/Users/lab%20506-12/OneDrive/Desktop/Exam/ecomerceAnik/app/build.gradle.kts)
- Include the new coroutines play services dependency.

---

### 2. ViewModel Refinement

#### [MODIFY] [MainViewModel.kt](file:///C:/Users/lab%20506-12/OneDrive/Desktop/Exam/ecomerceAnik/app/src/main/java/com/example/ecomerce_anik/ui/viewmodel/MainViewModel.kt)
- Use `try-finally` in `signIn` and `signUp` to guarantee `_isLoading.value = false` is executed.
- Add more descriptive error logging.

---

### 3. Repository Improvements

#### [MODIFY] [AuthRepositoryImpl.kt](file:///C:/Users/lab%20506-12/OneDrive/Desktop/Exam/ecomerceAnik/data/repository/AuthRepositoryImpl.kt)
- Add explicit logging to help debug if the Firebase task hangs again.

## Verification Plan

### Manual Verification
- **Sign Up**: Attempt to create a new account and verify the spinner stops and it either succeeds or shows an error.
- **Login**: Attempt to log in and verify the loading indicator resets correctly on both success and failure.
- **Network Check**: Verify it handles "No Internet" gracefully without hanging.

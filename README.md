Project Title: APK Inspector

Team member names: Chandler Ngo

Project description: Android APK extraction and DEX analysis application built with Kotlin and Jetpack Compose.

Problem the application solves: Extracting APK data from any application through an app in an organized, simple manner

Main features: Application browser, search, and details. APK File Structure View, and Extract APK to downloads. Home Screen Navigation of browser, APK details, and Downloads Folder

Technologies used: Kotlin, Jetpack Compose, Navigation Compose, Github, Android Studio, Android PackageManager and Storage Access Framework

Architecture overview:
UI:
- AppListScreen.kt
- ApkDetailsScreen.kt
- HistoryScreen.kt
- AppNavigation.kt
ViewModel:
- AppListViewModel.kt
- ApkDetailsViewModel.kt
Data Layer:
- InstalledAppRepository.kt
- ApkAnalyzer.kt
- ApkExporter.kt
Model Layer:
- InstalledApp.kt
- ApkFileEntry.kt
  
Screenshots of all major screens:

Application Browser:

<img width="322" height="725" alt="image" src="https://github.com/user-attachments/assets/c159843f-b87a-48b2-bf15-669bfae2f7d8" />


APK File Structure and Extract Option:

<img width="325" height="727" alt="image" src="https://github.com/user-attachments/assets/df88c677-883c-4702-967b-25c6c21ce28d" />


Analysis History:

<img width="326" height="728" alt="image" src="https://github.com/user-attachments/assets/a0b6601c-8ca2-4a38-bb1e-8639a6fd8e8d" />


Installation/run instructions:
Requirements
- Android Studio
- Android SDK
- Git
- Android emulator or physical Android device running Android 8.0 (API 26) or newer

Steps
1. Clone the GitHub repository:
- git clone https://github.com/chandlerngo-tech/APKInspectorApp.git
2. Open Android Studio.
3. Select Open and choose the cloned APKInspectorApp folder.
4. Allow Gradle to synchronize and install any missing Android SDK dependencies.
5. Start an Android emulator or connect a physical Android device with USB debugging enabled.
6. Click Run in Android Studio.
7. Once APK Inspector launches, select an installed application to inspect its APK contents.
8. Press Extract APK to choose a destination and export the APK file.

Team member contributions:
Chandler Ngo responsible for all Android Studio programming and project work done using Kotlin and Jetpack Compose

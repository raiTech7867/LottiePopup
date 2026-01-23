[![Maven Central](https://img.shields.io/maven-central/v/com.github.RaiTech/lottpop?label=Maven%20Central)](https://central.sonatype.com/artifact/com.github.RaiTech/lottpop)
[![JitPack](https://jitpack.io/v/RaiTech/LottiePop.svg)](https://jitpack.io/#RaiTech/LottiePop)
[![License](https://img.shields.io/badge/License-MIT-brightgreen.svg)](LICENSE)
[![Compose](https://img.shields.io/badge/Jetpack_Compose-1.6+-orange.svg)](https://developer.android.com/jetpack/compose)

# ✨ LottiePop

A **modern Glassmorphism popup dialog** with optional **Lottie animation** for **Jetpack Compose**.  
Perfect for success, error, info, and confirmation dialogs in your Android apps.

## ✨ Features

- 🎨 Stunning **Glassmorphism** UI with backdrop blur effects
- 🎬 Optional **Lottie** animations (via `LottieAnimationView` Compose integration)
- ⚡ Ultra-smooth **enter/exit animations** with spring physics
- 🧩 **Fully customizable** via `LottiePopupStyle`
- 📱 **Responsive** design for phones & tablets
- 🛡️ **Preview-safe** for Android Studio composables
- 🚀 **Compose-first** API, no XML needed
- 🔒 Lightweight (~50KB), no heavy dependencies

## 📦 Installation

### Via JitPack (Recommended for v1.0.0)

**settings.gradle.kts** (or `settings.gradle`):
```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

**Module-level `build.gradle.kts`**:
```kotlin
dependencies {
    implementation("com.github.RaiTech:LottiePop:1.1.1")
}
```

*Pro tip: Publish to Maven Central later for `com.github.raitech:lottpop:1.1.1` badge.*

## 🚀 Quick Start

```kotlin
import com.raitech.lottpop.LottiePopup
import com.raitech.lottpop.LottiePopupStyle

LottiePopup(
    visible = showPopup,
    titleText = "Success! 🎉",
    bodyText = "Your transaction completed successfully.",
    animationRes = R.raw.success_check,  // Optional Lottie raw resource
    style = LottiePopupStyle(),  // Optional custom style
    onDismiss = { showPopup = false }
)
```

## 🎨 Advanced Customization

```kotlin
val glassStyle = LottiePopupStyle(
    titleTextStyle = TextStyle(
        color = Color.Cyan,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.SansSerif
    ),
    bodyTextStyle = TextStyle(color = Color.White.copy(alpha = 0.9f)),
    screenBackgroundColor = Color.Black.copy(alpha = 0.6f),
    containerBackground = Brush.linearGradient(
        colors = listOf(Color.White.copy(alpha = 0.15f), Color.White.copy(alpha = 0.05f))
    ),
    cornerRadius = 28.dp,
    elevation = 12.dp
)

LottiePopup(
    visible = true,
    titleText = "Custom Glass Popup",
    bodyText = "Fully styled with gradients & blur effects.",
    animationRes = R.raw.confetti,
    style = glassStyle,
    lottieModifier = Modifier.size(120.dp),
    onDismiss = { /* Handle dismiss */ }
)
```

## 📋 API Reference

| Parameter | Type | Description | Default |
|-----------|------|-------------|---------|
| `visible` | `Boolean` | Controls popup visibility | - |
| `titleText` | `String` | Main title text | "" |
| `bodyText` | `String` | Body message text | "" |
| `animationRes` | `Int?` (R.raw.*) | Lottie animation resource (optional) | `null` |
| `style` | `LottiePopupStyle` | Custom styling | Defaults |
| `lottieModifier` | `Modifier` | Size/control for Lottie | `size(100.dp)` |
| `onDismiss` | `() -> Unit` | Called on outside tap/back press | {} |

## 🧪 Android Studio Preview

Safe for previews with mock data:

```kotlin
@Preview(showSystemUi = true, device = "spec:shape=Normal,width=360,height=800,unit=dp,dpi=480")
@Composable
fun PreviewLottiePopup() {
    var visible by remember { mutableStateOf(true) }
    LottiePopup(
        visible = visible,
        titleText = "Preview Mode ✅",
        bodyText = "Works perfectly in Android Studio!",
        animationRes = R.raw.mic_animation,  // Add your raw assets
        onDismiss = { visible = false }
    )
}
```

## 📄 License

MIT License - see LICENSE file.

## ❤️ Acknowledgments

Built with ❤️ for the Compose community. Inspired by modern glassmorphism trends.

⭐ **Star the repo** if this saves you time! Contributions welcome via PRs.

---

# MIT LICENSE

MIT License

Copyright (c) 2026 RaiTech

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

---

### Features
- Modern Glasmorphism UI with backdrop blur
- Optional Lottie animations
- Fully customizable styling
- No XML required - Pure Compose
- Smooth animations

---

## Topics

. Topics:
   - jetpack-compose
   - android
   - kotlin
   - lottie
   - glassmorphism
   - android-library
   - compose-ui
   - popup
   - dialog
   - ui-animation

---

## Performance Metrics

- Library size: ~50KB
- Min API: 24 (Android 7.0+)
- Compose version: 1.6+
- No reflection or dynamic loading
- Preview-safe (no initialization side effects)

---

Made by RaiTech - Creators of Bachat Khata Smart Finance app
⭐ Please star if you find this library useful!
![Screen_recording_20260120_023154](https://github.com/user-attachments/assets/addb3f59-fd74-4264-8f67-50ad5aa5382e)
![Screen_recording_20260120_023020](https://github.com/user-attachments/assets/a847a182-11b2-4704-99d2-6e602fda8afd)


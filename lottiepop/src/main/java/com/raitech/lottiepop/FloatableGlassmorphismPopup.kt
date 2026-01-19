package com.raitech.lottiepop

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

/**
 * Configurable Glassmorphism popup style.
 */
@Immutable
data class LottiePopupStyle(
    val titleTextStyle: TextStyle = TextStyle(
        color = Color(0xFFFFD700), // Gold-ish for attention
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold
    ),
    val bodyTextStyle: TextStyle = TextStyle(
        color = Color.White.copy(alpha = 0.9f),
        fontSize = 16.sp
    ),
    val screenBackgroundColor: Color = Color.White.copy(alpha = 0.6f),
    val dialogBackgroundColor: Color =  Color.White.copy(alpha = 0.1f),
    val cornerRadius: Dp = 28.dp,
    val blurRadius: Dp = 20.dp,
    val borderBrush: Brush =Brush.linearGradient(
        listOf(Color(0xFF6A5ACD).copy(alpha = 0.4f), Color(0xFF00BFFF).copy(alpha = 0.3f))
    )
)

/**
 * Simple Glassmorphism popup dialog with optional Lottie animation.
 *
 * @param visible Show or hide popup
 * @param titleText Popup title
 * @param bodyText Popup message
 * @param animationRes Optional Lottie raw resource (`R.raw.xxx`)
 * @param style Optional custom style
 * @param onDismiss Called when user dismisses popup
 */
@Composable
fun LottiePopup(
    visible: Boolean,
    titleText: String,
    bodyText: String,
    animationRes: Int? = null,
    style: LottiePopupStyle = LottiePopupStyle(),
    lottieModifier: Modifier = Modifier.size(100.dp), // Default
    onDismiss: () -> Unit
) {
    if (!visible) return

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn() + scaleIn(initialScale = 0.85f),
            exit = fadeOut() + scaleOut(targetScale = 0.85f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(style.screenBackgroundColor)
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(style.cornerRadius))
                        .background(style.dialogBackgroundColor)
                        .border(1.dp, style.borderBrush, RoundedCornerShape(style.cornerRadius))
                ) {
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .clip(RoundedCornerShape(style.cornerRadius))
                            .blur(style.blurRadius)
                    )

                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            IconButton(
                                onClick = onDismiss,
                                modifier = Modifier
                                    .size(25.dp)
                                    .align(Alignment.TopStart)
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_cancel),
                                    contentDescription = "Dismiss",
                                    tint = Color.White
                                )
                            }

                            Text(
                                text = titleText,
                                modifier = Modifier.align(Alignment.Center),
                                style = style.titleTextStyle,
                                textAlign = TextAlign.Center
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = bodyText,
                            style = style.bodyTextStyle,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterHorizontally)
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        animationRes?.let { res ->
                            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(res))
                            LottieAnimation(
                                composition,
                                iterations = LottieConstants.IterateForever,
                                modifier = lottieModifier // Use user-provided modifier
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, backgroundColor = 0xFF333333)
@Composable
fun LottiePopupPreview() {
    // Use a placeholder animation resource for preview
    LottiePopup(
        visible = true,
        titleText = "Success!",
        bodyText = "Your LottiePop is working 🎉",
        animationRes = R.raw.mic_animation, // Replace with a sample Lottie raw file in your project
        onDismiss = {}
    )
}

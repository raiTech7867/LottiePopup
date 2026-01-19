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
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
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
 * Style configuration for Glassmorphism popup.
 */
@Immutable
data class GlassPopupStyle(
    val titleTextStyle: TextStyle = TextStyle(
        color = Color.White,
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold
    ),
    val bodyTextStyle: TextStyle = TextStyle(
        color = Color.White.copy(alpha = 0.95f),
        fontSize = 15.sp
    ),
    val screenBackgroundColor: Color = Color.Black.copy(alpha = 0.6f),
    val dialogBackgroundColor: Color = Color.White.copy(alpha = 0.15f),
    val cornerRadius: Dp = 28.dp,
    val blurRadius: Dp = 20.dp,
    val borderBrush: Brush = Brush.linearGradient(
        listOf(Color.White.copy(alpha = 0.4f), Color.White.copy(alpha = 0.2f))
    )
)

/**
 * Glassmorphism Popup Dialog with optional animation slot.
 * Users can provide any @Composable via animationContent.
 */
@Composable
fun GlassPopupDialog(
    visible: Boolean,
    titleText: String,
    bodyText: String,
    style: GlassPopupStyle = GlassPopupStyle(),
    onDismiss: () -> Unit,
    animationContent: (@Composable () -> Unit)? = null // Slot API for custom animation
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
                    // Glass blur layer
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
                            // Cancel button top-left
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

                            // Title text centered
                            Text(
                                text = titleText,
                                modifier = Modifier.align(Alignment.Center),
                                style = style.titleTextStyle,
                                textAlign = TextAlign.Center
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // Body text
                        Text(
                            text = bodyText,
                            style = style.bodyTextStyle,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterHorizontally)
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        // Optional animation/content slot
                        animationContent?.invoke()

                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true, backgroundColor = 0xFF8A8A8A)
@Composable
fun GlassPopupExample() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.mic_animation))

    GlassPopupDialog(
        visible = true,
        titleText = "Listening...",
        bodyText = "Speak something",
        onDismiss = {},
        animationContent = {
            LottieAnimation(
                composition,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier.size(100.dp)
            )
        }
    )
}

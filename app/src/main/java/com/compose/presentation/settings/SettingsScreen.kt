package com.compose.presentation.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.presentation.ui.theme.Color3
import com.compose.presentation.ui.theme.Color4

@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Clean Notes",
            color = Color3,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, start = 32.dp, end = 32.dp),
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
        )
        Text(
            text = "\u2022 MVVM Clean Architecture",
            color = Color4,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 32.dp, end = 32.dp),
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
        )
        Text(
            text = "\u2022 Jetpack Compose",
            color = Color4,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 32.dp, end = 32.dp),
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
        )
        Text(
            text = "\u2022 Jetpack Navigation",
            color = Color4,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 32.dp, end = 32.dp),
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
        )
        Text(
            text = "\u2022 Dagger Hilt for dependency injection",
            color = Color4,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 32.dp, end = 32.dp),
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
        )
        Text(
            text = "\u2022 Room Database",
            color = Color4,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 32.dp, end = 32.dp),
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
        )
        Text(
            text = "\u2022 Kotlin Coroutines",
            color = Color4,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 32.dp, end = 32.dp),
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
        )
        Text(
            text = "\u2022 Kotlin Flow",
            color = Color4,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 32.dp, end = 32.dp),
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
        )
        val uriHandler = LocalUriHandler.current
        val annotatedGithub = buildAnnotatedString {
            pushStringAnnotation(
                tag = "URL",
                annotation = "https://github.com/iammehmetclk",
            )
            withStyle(
                style = SpanStyle(
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline,
                    fontSize = 16.sp,
                )
            ) {
                append("github.com/iammehmetclk")
            }
            pop()
        }
        val annotatedLinkedIn = buildAnnotatedString {
            pushStringAnnotation(
                tag = "URL",
                annotation = "https://www.linkedin.com/in/iammehmetclk/",
            )
            withStyle(
                style = SpanStyle(
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline,
                    fontSize = 16.sp,
                )
            ) {
                append("linkedin.com/in/iammehmetclk")
            }
            pop()
        }
        ClickableText(
            text = annotatedGithub,
            onClick = { offset ->
                annotatedGithub.getStringAnnotations(
                    tag = "URL",
                    start = offset,
                    end = offset,
                ).firstOrNull()?.let { annotation ->
                    uriHandler.openUri(annotation.item)
                }
            },
            modifier = Modifier.padding(top = 48.dp),
        )
        ClickableText(
            text = annotatedLinkedIn,
            onClick = { offset ->
                annotatedLinkedIn.getStringAnnotations(
                    tag = "URL",
                    start = offset,
                    end = offset,
                ).firstOrNull()?.let { annotation ->
                    uriHandler.openUri(annotation.item)
                }
            },
            modifier = Modifier.padding(top = 16.dp),
        )
    }
}
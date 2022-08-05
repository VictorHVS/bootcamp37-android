package com.example.breweri.presentation.common

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.breweri.presentation.components.BeesTopBar
import com.example.breweri.ui.theme.Yellow

@Composable
fun BeesScaffold(
    toolbar: @Composable () -> Unit = { BeesTopBar() },
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            toolbar()
        },
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        content = {
            Box {
                BackGroundCanvas()
                content()
            }
        }
    )
}

@Composable
fun BackGroundCanvas() {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(230.dp)
    ) {
        drawRoundRect(
            size = Size(size.width, size.height * .75f),
            color = Yellow
        )
        drawArc(
            color = Yellow,
            startAngle = 0f,
            sweepAngle = 180f,
            useCenter = true,
            size = Size(size.width, size.height * .75f),
            topLeft = Offset(0f, size.height * 0.37f)
        )
    }
}

@Preview(
    showBackground = true,
    name = "Light Mode"
)
@Composable
fun BeesScaffoldPreview() {
    BeesScaffold {
        Text(
            text = "OLA"
        )
    }
}
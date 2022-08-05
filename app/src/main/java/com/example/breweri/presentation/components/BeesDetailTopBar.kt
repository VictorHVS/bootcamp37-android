package com.example.breweri.presentation.components

import android.content.res.Configuration
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.breweri.R
import com.example.breweri.ui.theme.Black
import com.example.breweri.ui.theme.Yellow

@Composable
fun BeesDetailTopBar(
    title: String = "",
    onFilterClicked: () -> Unit = {},
    onBackClicked: () -> Unit = {}
) {
    TopAppBar(
        backgroundColor = Black,
        title = {
            Text(
                text = title,
                color = Yellow,
                style = MaterialTheme.typography.h5
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClicked) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.top_bar_back_arrow),
                    tint = Yellow
                )
            }
        },
        actions = {
            IconButton(onClick = onFilterClicked) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_filter),
                    contentDescription = stringResource(R.string.icon_filter),
                    tint = Yellow
                )
            }
        }
    )
}

@Preview(
    showBackground = true,
    name = "Light Mode"
)
@Composable
fun BeesDetailTopBarPreview() {
    BeesDetailTopBar(title = "Detalhes da Cervejaria")
}
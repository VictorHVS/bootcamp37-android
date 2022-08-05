package com.example.breweri.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.breweri.R
import com.example.breweri.ui.theme.Black
import com.example.breweri.ui.theme.Yellow

@Composable
fun BeesTopBar(
    onFilterClicked: () -> Unit = {}
) {
    TopAppBar(
        backgroundColor = Black,
        title = {
            Image(
                painter = painterResource(id = R.drawable.ic_vectorbrewery_icon),
                contentDescription = stringResource(id = R.string.app_name),
                modifier = Modifier.size(32.dp),
                colorFilter = ColorFilter.tint(Yellow)
            )
        },
        actions = {
            IconButton(onClick = onFilterClicked) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_filter),
                    contentDescription = stringResource(R.string.icon_filter),
                    tint = Yellow,
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
fun BeesTopBarPreview() {
    BeesTopBar()
}
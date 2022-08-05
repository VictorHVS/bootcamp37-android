package com.example.breweri.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.breweri.presentation.components.BreweryRatingIndicator
import com.example.breweri.ui.theme.BreweryPlaceHolderBg
import com.example.breweri.ui.theme.BreweryPlaceHolderText
import com.example.breweri.ui.theme.White


@Composable
fun BreweryItem(
    name: String,
    type: String,
    stars: Float,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = White, shape = RoundedCornerShape(15.dp))
            .clickable { onClick.invoke() }
            .padding(all = 16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(BreweryPlaceHolderBg, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = name[0].toString(),
                    style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold),
                    color = BreweryPlaceHolderText
                )
            }

            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.subtitle1,
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = type,
                        style = MaterialTheme.typography.caption
                    )
                    BreweryRatingIndicator(stars = stars)
                }
            }
        }
    }
}

@Preview
@Composable
private fun BreweryItemPreview() {
    BreweryItem(
        name = "Cervejaria A",
        type = "Tipo",
        stars = 3.9f
    )
}
package com.example.breweri.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.breweri.ui.theme.RatingBarActive
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.gowtham.ratingbar.RatingBarStyle
import com.gowtham.ratingbar.StepSize

val ratingBarStyle = RatingBarConfig()
    .stepSize(StepSize.HALF)
    .isIndicator(false)
    .style(RatingBarStyle.HighLighted)
    .activeColor(RatingBarActive)
    .inactiveBorderColor(RatingBarActive)

@Composable
fun BreweryRatingIndicator(stars: Float = 0f) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "$stars",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.caption
        )
        Spacer(modifier = Modifier.size(3.dp))
        RatingBar(
            value = stars.toInt().toFloat(),
            onValueChange = {},
            onRatingChanged = {},
            config = ratingBarStyle.size(18.dp)
        )
    }
}

@Composable
fun BreweryRating(value: Float, onValueChange: (Float) -> Unit) {
    RatingBar(
        value = value,
        onValueChange = onValueChange, onRatingChanged = {

        },
        config = ratingBarStyle.size(34.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun RatingPreview() {
    BreweryRatingIndicator(stars = 3.75f)
}
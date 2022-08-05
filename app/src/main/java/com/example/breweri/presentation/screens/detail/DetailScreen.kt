package com.example.breweri.presentation.screens.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.breweri.R
import com.example.breweri.domain.model.Brewery
import com.example.breweri.presentation.common.BeesScaffold
import com.example.breweri.presentation.components.BeesDetailTopBar
import com.example.breweri.presentation.components.BreweryRatingIndicator
import com.example.breweri.presentation.screens.rate_dialog.ShowRatingModal
import com.example.breweri.ui.theme.*
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun DetailScreen(
    navController: NavHostController,
    detailViewModel: DetailViewModel = hiltViewModel()
) {
    val brewery by detailViewModel.selectedBrewery.collectAsState()

    BeesScaffold(
        toolbar = {
            BeesDetailTopBar(
                title = stringResource(id = R.string.brewery_detail),
                onBackClicked = { navController.popBackStack() }
            )
        },
        content = {

            if (brewery == null)
                return@BeesScaffold


            val skipHalfExpanded by remember { mutableStateOf(true) }
            val state = rememberModalBottomSheetState(
                initialValue = ModalBottomSheetValue.Hidden,
                skipHalfExpanded = skipHalfExpanded
            )

            val keyboardController = LocalSoftwareKeyboardController.current
            if (!state.isVisible) {
                keyboardController!!.hide()
            }

            val scope = rememberCoroutineScope()

            ShowRatingModal(
                brewery!!,
                modifier = Modifier.padding(top = 40.dp, start = 18.dp, end = 18.dp),
                sheetState = state,
                onClose = {
                    scope.launch {
                        state.hide()
                    }
                }
            ) {
                BreweryDetail(brewery!!) {
                    scope.launch {
                        state.show()
                    }
                }
            }
        }
    )
}

@Composable
fun BreweryDetail(
    model: Brewery,
    onReviewClicked: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = 10.dp
    ) {
        Column(modifier = Modifier.padding(start = 24.dp, top = 20.dp, end = 24.dp)) {
            HeaderDetail(model)
            Spacer(Modifier.height(16.dp))
            RowElement(stringResource(id = R.string.detail_type), model.type)

            Spacer(Modifier.height(16.dp))
            Divider(color = DividerColor)
            RowElement(stringResource(id = R.string.detail_website), model.websiteUrl ?: "")

            Spacer(Modifier.height(16.dp))
            Divider(color = DividerColor)
            AddressElement(
                model.street ?: "",
                model.city,
                model.country,
                model.latitude,
                model.longitude
            )

            Spacer(Modifier.height(16.dp))
            Divider(color = DividerColor)
            FooterDetail(false, onReviewClicked)
        }
    }
}

@Composable
fun RoundText(text: Char) {
    Box(
        modifier = Modifier
            .size(55.dp)
            .background(BreweryPlaceHolderBg, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text.toString(),
            style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.Bold),
            color = BreweryPlaceHolderText
        )
    }
}

@Composable
fun HeaderDetail(brewery: Brewery) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RoundText(brewery.name[0])
        Column(
            modifier = Modifier
                .padding(start = 10.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = brewery.name, style = MaterialTheme.typography.h5)

            val ratingNumber = when {
                brewery.ratingNumber > 500 -> {
                    "+ de 500 avaliações"
                }
                else -> {
                    "${brewery.ratingNumber} avaliações"
                }

            }
            BreweryRatingIndicator(stars = brewery.display5StarsRating())
            Text(ratingNumber, color = Color.LightGray)
        }
    }
}

@Composable
fun RowElement(label: String, value: String) {
    Row(modifier = Modifier.height(40.dp)) {
        Text(
            label,
            style = RobotoTypography.h5,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        Text(
            text = value,
            style = RobotoTypography.body1,
            modifier = Modifier
                .padding(start = 60.dp)
                .fillMaxWidth()
                .align(Alignment.CenterVertically), textAlign = TextAlign.End
        )
    }
}

@Composable
fun AddressElement(
    street: String,
    city: String,
    country: String,
    latitude: String?,
    longitude: String?
) {
    val context = LocalContext.current
    Column(modifier = Modifier.padding(top = 10.dp)) {
        Row {
            Text(
                stringResource(id = R.string.detail_address),
                style = RobotoTypography.h5,
                modifier = Modifier.align(Alignment.Top)
            )
            Column {
                Text(
                    street,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                    style = RobotoTypography.body1,
                )
                Text(
                    city,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                    style = RobotoTypography.body1,
                )
                Text(
                    country,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                    style = RobotoTypography.body1,
                )
            }
        }
        if (latitude != null && longitude != null)
            Row(
                modifier = Modifier
                    .clickable {
                        openMaps(context, latitude, longitude)
                    }) {
                Image(
                    modifier = Modifier
                        .size(40.dp)
                        .padding(start = 10.dp)
                        .alpha(alpha = 1.0f),
                    painter = painterResource(android.R.drawable.ic_dialog_map),
                    colorFilter = ColorFilter.tint(color = Yellow),
                    contentDescription = stringResource(id = R.string.icon_map)
                )
                Text(
                    stringResource(id = R.string.detail_see_on_map),
                    modifier = Modifier.padding(top = 10.dp, start = 10.dp)
                )
            }
    }
}

fun openMaps(context: Context, latitude: String, longitude: String) {
    val uri = Uri.parse("geo:0,0?q=$latitude,$longitude")

    val mapIntent = Intent(Intent.ACTION_VIEW, uri)
    mapIntent.setPackage("com.google.android.apps.maps")

    context.startActivity(mapIntent)
}

@Composable
fun FooterDetail(
    isRated: Boolean,
    onReviewClicked: () -> Unit
) {

    Row {
        if (!isRated) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp, bottom = 40.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = onReviewClicked) {
                    Text(
                        text = stringResource(id = R.string.button_rate_brewery),
                        style = MaterialTheme.typography.button
                    )
                }
            }
        } else {
            Image(
                modifier = Modifier
                    .size(100.dp)
                    .padding(start = 10.dp)
                    .alpha(alpha = 1.0f),
                painter = painterResource(id = R.drawable.ic_brew_success),
                contentDescription = stringResource(id = R.string.detail_brew_success)
            )
            Text(
                stringResource(id = R.string.detail_rating_block),
                color = Color.Green,
                modifier = Modifier.padding(top = 30.dp)
            )
        }
    }
}



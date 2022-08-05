package com.example.breweri.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.breweri.R
import com.example.breweri.domain.model.Brewery
import com.example.breweri.navigation.Screen
import com.example.breweri.presentation.common.BeesScaffold
import com.example.breweri.presentation.components.SearchWidget
import com.example.breweri.ui.theme.OverlineColor

@ExperimentalComposeUiApi
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val searchQuery by homeViewModel.searchQuery
    val breweries = homeViewModel.searchedBreweries.collectAsLazyPagingItems()

    BeesScaffold(
        content = {
            LazyColumn(
                Modifier
                    .fillMaxHeight()
                    .padding(start = 18.dp, end = 18.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.height(40.dp))
                    SearchHeader(
                        text = searchQuery,
                        onTextChange = {
                            homeViewModel.updateSearchQuery(query = it)
                        }, onSearchClicked = {
                            homeViewModel.searchBreweries(query = it)
                        })

                    val result = handleBreweryPagingResult(breweries)
                    if (result) {
                        BreweriesListHeader(total = breweries.itemCount)
                    }
                }

                items(items = breweries, key = { it.id }) { brewery ->
                    brewery?.let {
                        BreweryItem(
                            name = brewery.name,
                            type = brewery.type,
                            stars = 2.8f,
                            onClick = {
                                navController.navigate(Screen.Detail.passBreweryId(breweryId = brewery.id))
                            })
                        Spacer(modifier = Modifier
                            .height(16.dp)
                            .background(Color.Red))
                    }
                }
            }
        }
    )
}

@Composable
fun handleBreweryPagingResult(breweries: LazyPagingItems<Brewery>): Boolean {
    breweries.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }

        return when {
            breweries.itemCount == 0 || error != null -> {
                Spacer(modifier = Modifier.height(36.dp))
                EmptyScreen()
                false
            }
            else -> true
        }
    }
}

@Composable
fun BreweriesListHeader(total: Int) {
    Column {
        Text(
            text = stringResource(id = R.string.home_screen_users_options_title),
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Start
        )
        Text(
            text = stringResource(
                id = R.string.home_screen_results,
                total,
                total
            ),
            style = MaterialTheme.typography.subtitle1,
            color = OverlineColor
        )
        Spacer(modifier = Modifier.height(26.dp))
    }
}

@ExperimentalComposeUiApi
@Composable
fun SearchHeader(
    text: String,
    onTextChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
) {
    Column {
        Text(
            text = stringResource(id = R.string.home_screen_welcome),
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Start
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(26.dp)
        )
        SearchWidget(
            text = text,
            onTextChange = onTextChange,
            onSearchClicked = onSearchClicked
        )
        Spacer(modifier = Modifier.height(100.dp))
    }
}

@ExperimentalComposeUiApi
@Preview(showBackground = true, backgroundColor = 0xFFF2F2F7)
@Composable
fun SearchHeaderPreview() {
    SearchHeader(text = "Click Here", onTextChange = { }, onSearchClicked = { })
}

@Preview(showBackground = true, backgroundColor = 0xFFF2F2F7)
@Composable
fun BreweryListPreview() {
    BreweriesListHeader(3)
}

@Preview
@Composable
fun HomeContentPreview() {

}

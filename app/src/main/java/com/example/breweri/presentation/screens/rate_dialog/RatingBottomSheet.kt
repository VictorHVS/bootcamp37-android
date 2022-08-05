package com.example.breweri.presentation.screens.rate_dialog

import android.util.Patterns
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.breweri.R
import com.example.breweri.domain.model.Brewery
import com.example.breweri.presentation.components.BreweryRating
import com.example.breweri.ui.theme.BlackText
import com.example.breweri.ui.theme.Yellow

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun ShowRatingModal(
    brewery: Brewery,
    sheetState: ModalBottomSheetState,
    onClose: () -> Unit,
    modifier: Modifier,
    content: @Composable () -> Unit
) {
    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            RatingForm(breweryName = brewery.name, onClose = onClose)
        }
    ) {
        Column(modifier = modifier) {
            content()
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun RatingForm(
    breweryName: String,
    onClose: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    var rating: Float by remember { mutableStateOf(0f) }
    var email: String by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = onClose) {
                Icon(
                    Icons.Default.Close,
                    contentDescription = stringResource(id = R.string.close)
                )
            }
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.rating_form_title, breweryName),
            style = MaterialTheme.typography.h3,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))
        BreweryRating(rating) {
            rating = it
        }
        Spacer(modifier = Modifier.height(24.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            placeholder = {
                Text(text = stringResource(id = R.string.placeholder_email))
            },
            isError = email.length > 3 && !isFormValid(email),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = MaterialTheme.colors.onSurface
            ),
            onValueChange = {
                email = it
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            ),
        )
        if (email.length > 3 && !isFormValid(email)) {
            Text(
                text = stringResource(id = R.string.error_invalid_email),
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .padding(start = 16.dp, top = 2.dp)
                    .fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(34.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { },
            colors = ButtonDefaults.buttonColors(
                disabledBackgroundColor = Yellow.copy(alpha = 0.30f),
                disabledContentColor = BlackText.copy(alpha = 0.30f)
            ),
            enabled = isFormValid(email)
        ) {
            Text(
                text = "SALVAR",
                style = MaterialTheme.typography.button
            )
        }
    }
}

fun isFormValid(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

@ExperimentalComposeUiApi
@Preview
@Composable
fun RatingFormPreview() {
    RatingForm("Boteco Gaspar", onClose = {})
}

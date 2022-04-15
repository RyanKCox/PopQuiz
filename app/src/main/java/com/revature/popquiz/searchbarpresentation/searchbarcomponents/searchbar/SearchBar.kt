/*
 * Copyright 2021 Paulo Pereira
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.presentation.searchbarcomponents.searchbar

import android.annotation.SuppressLint
import android.os.Build
import android.os.Build.VERSION_CODES
import android.os.Build.VERSION_CODES.O
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.example.androiddevchallenge.domain.models.Quiz
import com.example.androiddevchallenge.presentation.searchbarsample.AutoCompleteValueSample
import com.revature.popquiz.model.dataobjects.SearchWidgetState
import com.revature.popquiz.viewmodels.SearchBarViewModel


@Composable
fun UnclickedSearchBar(onSearchClicked: () -> Unit, headingText: String)
{
    TopAppBar(
        modifier = Modifier
            .padding(horizontal = 5.dp)
            .absolutePadding(top = 15.dp, bottom = 20.dp)
            .clip(shape = RoundedCornerShape(5.dp)),
        title =
        {
            Text(text = headingText)
        },
        actions =
        {
            IconButton(onClick = { onSearchClicked() })
            {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search Icon",
                    tint = Color.White
                )
            }
        }
    )
}

@Preview
@Composable
fun PreviewUnclickedSearchBar()
{
    UnclickedSearchBar(onSearchClicked = { /*TODO*/ }, headingText = "Saved Quizzes")
}

@Composable
fun ClickedSearchBar(
    headingText: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit
)
{
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp)
            .absolutePadding(top = 15.dp, bottom = 20.dp)
            .clip(shape = RoundedCornerShape(5.dp)),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.primary
    )
    {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = headingText,
            onValueChange =
            {
                onTextChange(it)
            },
            placeholder = {
                Text(
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    text = "Search here...",
                    color = Color.White
                )
            },
            textStyle = TextStyle(
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            ),
            singleLine = true,
            trailingIcon = {
                IconButton(
                    onClick =
                    {
                        if(headingText.isNotEmpty())
                        {
                            onTextChange("")
                        }
                        else
                        {
                            onCloseClicked()
                        }
                    }
                )
                {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = Color.White
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(headingText)
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.White.copy(alpha = ContentAlpha.medium)
            )
        )
    }
}

@Preview
@Composable
fun PreviewClickedSearchBar()
{
    ClickedSearchBar(
        headingText = "Saved Quizzes",
        onTextChange = {},
        onCloseClicked = {},
        onSearchClicked = {}
    )
}

@ExperimentalAnimationApi
@Composable
fun MainSearchBar(
    searchWidgetState: SearchWidgetState,
    searchTextState: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    onSearchTriggered: () -> Unit
)
{
    when(searchWidgetState)
    {
        SearchWidgetState.CLOSED ->
        {
            UnclickedSearchBar(
                onSearchClicked = onSearchTriggered,
                headingText = "Search"
            )
        }
        SearchWidgetState.OPENED ->
        {
            quizBarSearch()
        }
    }
}

@Composable
fun TextSearchBar(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    onDoneActionClick: () -> Unit = {},
    onClearClick: () -> Unit = {},
    onFocusChanged: (FocusState) -> Unit = {},
    onValueChanged: (String) -> Unit
)
{
    TextField(
        modifier = modifier
            .fillMaxWidth(.9f)
            .onFocusChanged { onFocusChanged(it) },
        value = value,
        onValueChange = { query ->
            onValueChanged(query)
        },
        label = { Text(text = label) },
        textStyle = MaterialTheme.typography.subtitle1,
        singleLine = true,
        trailingIcon = {
            IconButton(onClick = { onClearClick() }) {
                Icon(imageVector = Icons.Filled.Clear, contentDescription = "Clear")
            }
        },
        keyboardActions = KeyboardActions(onDone = { onDoneActionClick() }),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Text
        )
    )
}



@ExperimentalAnimationApi
@Composable
fun quizBarSearch(
) {
    val quizzes = listOf(
        Quiz(
            name = "Java",
            description = "Java basics quiz"
        ),
        Quiz(
            name = "Kotlin",
            description = "Kotlin fundamentals"
        ),
        Quiz(
            name = "Databases",
            description = "How do databases work in Kotlin and Jetpack Compose?"
        ),
        Quiz(
            name = "Jetpack Compose",
            description = "Intro to using Jetpack Compose in Android Studio"
        ),
    )
    val names = quizzes.map { it.name }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    )
    {
        AutoCompleteValueSample(items = names, searchBarViewModel = SearchBarViewModel())
    }
}



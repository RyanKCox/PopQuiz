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
package com.example.androiddevchallenge.presentation.searchbarsample

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.androiddevchallenge.presentation.searchbarcomponents.autocomplete.AutoCompleteBox

import com.example.androiddevchallenge.presentation.searchbarcomponents.autocomplete.utils.AutoCompleteSearchBarTag
import com.example.androiddevchallenge.presentation.searchbarcomponents.autocomplete.utils.asAutoCompleteEntities
import com.example.androiddevchallenge.presentation.searchbarcomponents.searchbar.TextSearchBar
import com.revature.popquiz.MainActivity
import com.revature.popquiz.viewmodel.SavedQuizVM
import com.revature.popquiz.viewmodels.SearchBarViewModel
import java.util.Locale


val autoCompleteItems = listOf(
    "Java",
    "Kotlin",
    "Databases",
    "Jetpack Compose",
    "REST API's"
)
val autoCompleteEntities = autoCompleteItems.asAutoCompleteEntities(
    filter =
    { item, query ->
        item.lowercase(Locale.getDefault())
            .startsWith(query.lowercase(Locale.getDefault()))
    }
)

@ExperimentalAnimationApi
@Composable
fun AutoCompleteValueSample(autoCompleteItems: List<String>)
{
    var context = LocalContext.current
    var searchBarViewModel = ViewModelProvider(context as MainActivity).get(SearchBarViewModel::class.java)
    var saveQuizVM = ViewModelProvider(context).get(SavedQuizVM::class.java)
//    var sReturn = ""
    AutoCompleteBox(
        autoCompleteItems = autoCompleteEntities,
        autoCompleteItemContent =
        { item ->
            ValueAutoCompleteItem(item.value)
        }
    )
    {
        var value by remember { mutableStateOf("") }
        val view = LocalView.current

        onItemSelected()
        { item ->
            value = item.value
            searchBarViewModel.sSearchValue = value
            saveQuizVM.sSearchValue = value

            filter(value)
            view.clearFocus()
        }

        TextSearchBar(
            modifier = Modifier.testTag(AutoCompleteSearchBarTag),
            value = value,
            label = "Search Quizzes",
            onDoneActionClick =
            {
                view.clearFocus()
            },
            onClearClick =
            {
                value = ""
                searchBarViewModel.sSearchValue = value
                saveQuizVM.sSearchValue = value
                filter(value)
                view.clearFocus()
            },
            onFocusChanged =
            {
                    focusState -> isSearching = focusState.hasFocus
            },
            onValueChanged =
            { query ->
                value = query
                searchBarViewModel.sSearchValue = value
                saveQuizVM.sSearchValue = value
                filter(value)
            }
        )
    }
}




@Composable
fun ValueAutoCompleteItem(item: String)
{
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    )
    {
        Text(
            text = item,
            style = MaterialTheme.typography.subtitle2
        )
    }
}

package com.revature.popquiz.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.revature.popquiz.model.dataobjects.SearchWidgetState

class SearchBarViewModel: ViewModel()
{
    private val _searchWidgetState: MutableState<SearchWidgetState> =
        mutableStateOf(value = SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState

    private val _searchTextState: MutableState<String> =
        mutableStateOf(value = "")
    val searchTextState: State<String> = _searchTextState

    fun updateSearchWidgetState(newValue: SearchWidgetState)
    {
        _searchWidgetState.value = newValue
    }

    fun updateSearchTextState(newValue: String)
    {
        _searchTextState.value = newValue
    }
}
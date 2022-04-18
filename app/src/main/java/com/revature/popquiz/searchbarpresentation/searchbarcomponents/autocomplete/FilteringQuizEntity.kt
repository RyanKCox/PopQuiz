package com.revature.popquiz.searchbarpresentation.searchbarcomponents.autocomplete

import androidx.compose.runtime.Stable

@Stable
interface FilteringQuizEntity
{
    fun filter(query: String): Boolean
}

@Stable
interface ValueFilteringQuizEntity<T> : FilteringQuizEntity
{
    val filtering_quiz_value: T
}

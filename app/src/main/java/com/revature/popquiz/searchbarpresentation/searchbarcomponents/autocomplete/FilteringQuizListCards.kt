package com.revature.popquiz.searchbarpresentation.searchbarcomponents.autocomplete.utils

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.revature.popquiz.searchbarpresentation.searchbarcomponents.autocomplete.FilteringQuizDesignScope
import com.revature.popquiz.searchbarpresentation.searchbarcomponents.autocomplete.FilteringQuizEntity
import com.revature.popquiz.searchbarpresentation.searchbarcomponents.autocomplete.FilteringQuizScope
import com.revature.popquiz.searchbarpresentation.searchbarcomponents.autocomplete.FilteringQuizState

const val FilteringQuizListTag = "FilteringQuizList"

@ExperimentalAnimationApi
@Composable
fun <T : FilteringQuizEntity> FilteringQuizListCards(
    items: List<T>,
    itemContent: @Composable (T) -> Unit,
    content: @Composable FilteringQuizScope<T>.() -> Unit
)
{
    val filteringQuizState = remember { FilteringQuizState(startItems = items) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        filteringQuizState.content()
        AnimatedVisibility(visible = filteringQuizState.isSearching)
        {
            LazyColumn(
                modifier = Modifier.filteringQuiz(filteringQuizState,),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                items(filteringQuizState.filteredItems)
                { item ->
                    Box(modifier = Modifier.clickable {filteringQuizState.selectItem(item)})
                    {
                        itemContent(item)
                    }
                }
            }
        }
    }
}

private fun Modifier.filteringQuiz(
    filteringQuizItemScope: FilteringQuizDesignScope
): Modifier = composed()
{
    val baseModifier = if (filteringQuizItemScope.shouldWrapContentHeight)
        wrapContentHeight()
    else
        heightIn(0.dp, filteringQuizItemScope.boxMaxHeight)

    baseModifier
        .testTag(FilteringQuizListTag)
        .fillMaxWidth(filteringQuizItemScope.boxWidthPercentage)
        .border(
            border = filteringQuizItemScope.boxBorderStroke,
            shape = filteringQuizItemScope.boxShape
        )
}
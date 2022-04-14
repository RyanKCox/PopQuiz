package com.revature.popquiz.view.shared


import android.app.ActionBar
import android.widget.SearchView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.navigation.NavController
import com.revature.popquiz.view.screens.quizTags

/**
 * Shared Composables
 *
 * This file is for composables that are used in multiple screens
 */

/**
 * QuizScaffold
 *
 * Scaffold to be used with all screens
 */
@Composable
fun QuizScaffold(sTitle:String, navController: NavController, content:@Composable () -> Unit)
{

    //Temp scaffold before we build it
    Scaffold(
        topBar =
        {
            TopAppBar(
                title = {Text(sTitle)},
                backgroundColor = MaterialTheme.colors.secondary
            )
        },
        content =
        {

            content()
        }
    )
}

/**
 * Temporary Scaffold that does not take in navController
 */
@Composable
fun TempQuizScaffold(sTitle: String, content: @Composable () -> Unit)
{
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBar (title = {Text(sTitle)}, backgroundColor = MaterialTheme.colors.secondary) },
        backgroundColor = MaterialTheme.colors.background,
        content = { content() }
    )
}

@Composable
fun UniversalButton(
    enabled: Boolean,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier
)
{
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
        shape = RoundedCornerShape(25.dp)
    )
    {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            color = Color.Black,
            fontSize = 20.sp
        )
    }
}

@Composable
fun SearchBar(onSearchClicked: () -> Unit, headingText: String)
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
fun PreviewSearchBar()
{
    SearchBar(onSearchClicked = { /*TODO*/ }, headingText = "Saved Quizzes")
}

@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit
)
{
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.primary
    )
    {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = text,
            onValueChange = {
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
                        if(text.isNotEmpty())
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
                    onSearchClicked(text)
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.White.copy(alpha = ContentAlpha.medium)
            )
        )
    }
}


@Composable
fun QuizCardForLazyColumn(
    quizTitleText: String,
    shortQuizDescriptionText: String
)
{
    Card(
        modifier =
        Modifier
            .clickable { }
            .height(150.dp)
            .fillMaxWidth()
            .absolutePadding(bottom = 10.dp)
            .padding(horizontal = 5.dp),
//            .border(
//                BorderStroke(
//                    3.dp,
//                    brush = Brush.horizontalGradient(
//                        colors = listOf(
//                            PurpleVariant,
//                            BluishGreen
//                        )
//                    )
//                ),
//                shape = RoundedCornerShape(25.dp)
//            ),

        shape = RoundedCornerShape(25.dp)
    )
    {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.absolutePadding(top = 10.dp)
        )
        {
            Text(
                text = quizTitleText,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Text(
                text = shortQuizDescriptionText,
                textAlign = TextAlign.Center
            )
        }

        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.absolutePadding(bottom = 10.dp)
        )
        {
            quizTags()
        }

    }
}

@Preview
@Composable
fun ViewQuizCard()
{
    QuizCardForLazyColumn("Quiz Title", "Short quiz description")
}
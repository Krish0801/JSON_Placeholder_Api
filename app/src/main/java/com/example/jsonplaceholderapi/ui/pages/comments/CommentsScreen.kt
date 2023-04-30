package com.example.jsonplaceholderapi.ui.pages.comments

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jsonplaceholderapi.data.model.comments.CommentsItemModel

@Composable
fun CommentsScreen(navController: NavController) {

    val viewModel = hiltViewModel<CommentsViewModel>()
    val commentsList by viewModel.comments.collectAsState()

    CommentsPage(commentsList)

}

@Composable
private fun CommentsPage(
    commentsList: ArrayList<CommentsItemModel>
) {

    val backgroundColor = Color(0xFFEDF6FC)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor)
    ) {
        ListComments(commentsList, LazyListState())
    }
}

@Composable
fun ListComments(
    commentsList: ArrayList<CommentsItemModel>,
    scrollState: LazyListState
) {
    val listState = rememberLazyListState()
    val textColor = Color(0xFFE88853)
    val cardColor = Color(0xFF0C3954)

    // Keep track of the index of the currently expanded post
    var expandedIndex by remember { mutableStateOf(-1) }

    LazyColumn(
        state = scrollState,
        modifier = Modifier.fillMaxWidth()
    ) {
        itemsIndexed(commentsList) { index, comments ->
            Card(
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .border(4.dp, textColor, shape = RoundedCornerShape(15.dp))
                    .clickable {
                        // If the clicked card is already expanded, collapse it
                        if (expandedIndex == index) {
                            expandedIndex = -1
                        } else {
                            // Expand the clicked card and collapse the previously expanded card
                            expandedIndex = index
                        }
                    }
            ) {
                Box(
                    modifier = Modifier
                        .background(color = if (expandedIndex == index) cardColor else cardColor)
                        .padding(10.dp)
                ) {
                    if (expandedIndex == index) {
                        Text(
                            text = comments.body.toString(),
                            color = textColor,
                            textAlign = TextAlign.Left,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                        )
                    } else {
                        Column(
                            modifier = Modifier
                                .background(color = cardColor)
                                .padding(10.dp)
                        ) {
                            Text(
                                text = "ID",
                                color = textColor,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.fillMaxWidth()
                            )

                            Text(
                                text = comments.id.toString(),
                                color = textColor,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                            )

                            Text(
                                text = "Name",
                                color = textColor,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.fillMaxWidth()
                            )

                            Text(
                                text = comments.name.toString(),
                                color = textColor,
                                textAlign = TextAlign.Center,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                            )
                            Text(
                                text = "Email",
                                color = textColor,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.fillMaxWidth()
                            )

                            Text(
                                text = comments.email.toString(),
                                color = textColor,
                                textAlign = TextAlign.Center,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
package com.example.jsonplaceholderapi.ui.pages.albums

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jsonplaceholderapi.data.model.albums.AlbumsItemModel

@Composable
fun AlbumsScreen(navController: NavController) {

    val viewModel = hiltViewModel<AlbumsViewModel>()
    val albumsList by viewModel.albums.collectAsState()

    AlbumsPage(albumsList)

}

@Composable
private fun AlbumsPage(
    albumsList: ArrayList<AlbumsItemModel>
) {

    val backgroundColor = Color(0xFFEDF6FC)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor)
    ) {
        ListAlbums(albumsList, LazyListState())
    }
}

@Composable
fun ListAlbums(
    albumsList: ArrayList<AlbumsItemModel>,
    scrollState: LazyListState
) {
    val listState = rememberLazyListState()
    val textColor = Color(0xFFE88853)
    val cardColor = Color(0xFF0C3954)

    LazyColumn(
        state = scrollState,
        modifier = Modifier.fillMaxWidth()
    ) {
        itemsIndexed(albumsList) { index, albums ->
            Card(
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .border(4.dp, textColor, shape = RoundedCornerShape(15.dp))
            ) {
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
                        text = albums.id.toString(),
                        color = textColor,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )

                    Text(
                        text = "Title",
                        color = textColor,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Text(
                        text = albums.title.toString(),
                        color = textColor,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )
                }
            }
        }
    }
}

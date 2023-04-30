package com.example.jsonplaceholderapi.ui.pages.photos

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.jsonplaceholderapi.data.model.photos.PhotosItemModel

@Composable
fun PhotosScreen(navController: NavController){

    val viewModel = hiltViewModel<PhotosViewModel>()
    val photosList by viewModel.photos.collectAsState()

    PhotosPage(photosList)

}

@Composable
private fun PhotosPage(
    photosList: ArrayList<PhotosItemModel>) {

    val backgroundColor = Color(0xFFEDF6FC)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor)
    ) {
        ListPhotos(photosList, LazyListState())
    }
}

@Composable
fun ListPhotos(
    photosList: ArrayList<PhotosItemModel>,
    scrollState: LazyListState
) {
    val listState = rememberLazyListState()
    val cardColor = Color(0xFFE88853)
    val textColor = Color(0xFF0C3954)


    LazyColumn(
        state = scrollState,
        modifier = Modifier.fillMaxWidth()
    ) {
        itemsIndexed(photosList) { index, photos ->
            Card(
                shape = RoundedCornerShape(15.dp),
                elevation = 8.dp,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .border(4.dp, cardColor, shape = RoundedCornerShape(15.dp))

            ) {
                Box {
                    Image(
                        painter = rememberAsyncImagePainter(model = photos.url.toString()),
                        contentDescription = "photos",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp),
                        contentScale = ContentScale.Crop,
                    )
                    Row(
                        modifier = Modifier
                            .alpha(.9f)
                            .fillMaxWidth()
                            .background(
                                brush = Brush.horizontalGradient(
                                    listOf(
                                        Color(0xFF1C3646),
                                        Color(0xFF0F76B4)
                                    )
                                )
                            )
                            .padding(16.dp)
                            .align(Alignment.BottomStart),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = photos.title.toString(),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = cardColor,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                    }
                }
            }
        }
    }
}
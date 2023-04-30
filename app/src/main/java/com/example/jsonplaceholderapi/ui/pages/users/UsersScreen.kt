package com.example.jsonplaceholderapi.ui.pages.users

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jsonplaceholderapi.data.model.users.UsersItemModel

@Composable
fun UsersScreen(navController: NavController){

    val viewModel = hiltViewModel<UsersViewModel>()
    val usersList by viewModel.users.collectAsState()

    UsersPage(usersList)

}

@Composable
private fun UsersPage(
    usersList: ArrayList<UsersItemModel>
) {
    val backgroundColor = Color(0xFFEDF6FC)

    // Remember the list of users and reset the scroll position when the composable is re-composed
    val scrollState = rememberScrollState(0)
    val rememberedUsersList = rememberSaveable { usersList }
    LaunchedEffect(rememberedUsersList) {
        scrollState.scrollTo(0)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor)
    ) {
        ListUsers(usersList, LazyListState())
    }
}

@Composable
fun ListUsers(
    usersList: ArrayList<UsersItemModel>,
    scrollState: LazyListState
) {
    val textColor = Color(0xFFE88853)
    val cardColor = Color(0xFF0C3954)

    LazyColumn(
        state = scrollState,
        modifier = Modifier.fillMaxWidth()
    ) {
        itemsIndexed(usersList) { index, users ->
            Card(
                shape = RoundedCornerShape(15.dp),
                elevation = 8.dp,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .border(4.dp, textColor, shape = RoundedCornerShape(15.dp))
//                    .clickable{
//                        navigateToDetails(brewery.id!!)
//                    }

            ) {
                Column(
                    modifier = Modifier
                        .background(color = cardColor)
                        .padding(10.dp)
                ) {
                    Text(
                        text = "User ID",
                        color = textColor,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Text(
                        text = users.id.toString(),
                        color = textColor,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )

                    Text(
                        text = "User Name",
                        color = textColor,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Text(
                        text = users.name.toString(),
                        color = textColor,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )

                    Text(
                        text = "User Email",
                        color = textColor,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Text(
                        text = users.email.toString(),
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

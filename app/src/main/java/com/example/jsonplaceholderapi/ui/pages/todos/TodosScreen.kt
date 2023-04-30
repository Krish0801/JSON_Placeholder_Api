package com.example.jsonplaceholderapi.ui.pages.todos

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jsonplaceholderapi.data.model.todos.TodosItemModel

@Composable
fun TodosScreen(navController: NavController){

    val viewModel = hiltViewModel<TodosViewModel>()
    val todosList by viewModel.todos.collectAsState()

    TodosPage(todosList,LazyListState())

}

@Composable
fun TodosPage(todosList: List<TodosItemModel>,
              scrollState: LazyListState) {
    val backgroundColor = Color(0xFFEDF6FC)
    val textColor = Color(0xFFE88853)
    val selectedColor = Color(0xFFE88853)
    val unselectedColor = Color(0xFF0C3954)
    val selectedColor1 = Color(0xFF3DE100)
    val unselectedColor1 = Color(0xFFFE0001)

    var selectedFilter by rememberSaveable { mutableStateOf(FilterType.ALL) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Filter: ",
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.width(16.dp))
            RadioButton(
                selected = selectedFilter == FilterType.ALL,
                onClick = { selectedFilter = FilterType.ALL },
                colors = RadioButtonDefaults.colors(selectedColor = selectedColor, unselectedColor = unselectedColor)
            )
            Text(text = "All", color = textColor, modifier = Modifier.clickable { selectedFilter = FilterType.ALL })
            Spacer(modifier = Modifier.width(16.dp))
            RadioButton(
                selected = selectedFilter == FilterType.TRUE,
                onClick = { selectedFilter = FilterType.TRUE },
                colors = RadioButtonDefaults.colors(selectedColor = selectedColor1, unselectedColor = unselectedColor)
            )
            Text(text = "True", color = textColor, modifier = Modifier.clickable { selectedFilter = FilterType.TRUE })
            Spacer(modifier = Modifier.width(16.dp))
            RadioButton(
                selected = selectedFilter == FilterType.FALSE,
                onClick = { selectedFilter = FilterType.FALSE },
                colors = RadioButtonDefaults.colors(selectedColor = unselectedColor1, unselectedColor = unselectedColor)
            )
            Text(text = "False", color = textColor, modifier = Modifier.clickable { selectedFilter = FilterType.FALSE })
        }

        val filteredTodos = when (selectedFilter) {
            FilterType.ALL -> todosList
            FilterType.TRUE -> todosList.filter { it.completed == true }
            FilterType.FALSE -> todosList.filter { it.completed == false }
        }

        LazyColumn(
            state = scrollState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)

        ) {
            items(filteredTodos) { todo ->
                TodoCard(todo)
            }
        }
    }
}

@Composable
fun TodoCard(todo: TodosItemModel) {

    val cardColor = Color(0xFF0C3954)
    val textColor = Color(0xFFE88853)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        backgroundColor = cardColor,
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp,
        border = BorderStroke(
            width = 4.dp,
            color = if (todo.completed == true) Color.Green else Color.Red)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "ID: ${todo.id}",
                fontWeight = FontWeight.Bold,
                color = textColor,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Title: ${todo.title}",
                fontWeight = FontWeight.Bold,
                color = textColor,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = "Completed: ${todo.completed}",
                fontWeight = FontWeight.Bold,
                color = if (todo.completed == true) Color.Green else Color.Red,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

enum class FilterType {
    ALL, TRUE, FALSE
}

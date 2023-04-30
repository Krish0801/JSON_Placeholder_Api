package com.example.jsonplaceholderapi.ui.pages.todos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jsonplaceholderapi.data.model.todos.TodosItemModel
import com.example.jsonplaceholderapi.data.model.users.UsersItemModel
import com.example.jsonplaceholderapi.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodosViewModel @Inject constructor(
    val repository: Repository
): ViewModel() {

    val _todos = MutableStateFlow(ArrayList<TodosItemModel>(emptyList()))
    var todos : StateFlow<ArrayList<TodosItemModel>> = _todos

    init {
        viewModelScope.launch{
            _todos.value= repository.getTodos()
        }
    }
}
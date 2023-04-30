package com.example.jsonplaceholderapi.ui.pages.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jsonplaceholderapi.data.model.users.UsersItemModel
import com.example.jsonplaceholderapi.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    val repository: Repository
): ViewModel() {

    val _users = MutableStateFlow(ArrayList<UsersItemModel>(emptyList()))
    var users : StateFlow<ArrayList<UsersItemModel>> = _users

    init {
        viewModelScope.launch{
            _users.value= repository.getUsers()
        }
    }
}
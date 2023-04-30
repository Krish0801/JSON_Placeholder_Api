package com.example.jsonplaceholderapi.ui.pages.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jsonplaceholderapi.data.model.posts.PostsItemModel
import com.example.jsonplaceholderapi.data.model.users.UsersItemModel
import com.example.jsonplaceholderapi.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    val repository: Repository
): ViewModel() {

    val _posts = MutableStateFlow(ArrayList<PostsItemModel>(emptyList()))
    var posts : StateFlow<ArrayList<PostsItemModel>> = _posts

    init {
        viewModelScope.launch{
            _posts.value= repository.getPosts()
        }
    }
}
package com.example.jsonplaceholderapi.ui.pages.comments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jsonplaceholderapi.data.model.albums.AlbumsItemModel
import com.example.jsonplaceholderapi.data.model.comments.CommentsItemModel
import com.example.jsonplaceholderapi.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor(
    val repository: Repository
): ViewModel() {

    val _comments = MutableStateFlow(ArrayList<CommentsItemModel>(emptyList()))
    var comments : StateFlow<ArrayList<CommentsItemModel>> = _comments

    init {
        viewModelScope.launch{
            _comments.value= repository.getComments()
        }
    }
}
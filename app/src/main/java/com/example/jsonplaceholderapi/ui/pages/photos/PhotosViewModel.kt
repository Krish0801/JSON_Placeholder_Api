package com.example.jsonplaceholderapi.ui.pages.photos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jsonplaceholderapi.data.model.photos.PhotosItemModel
import com.example.jsonplaceholderapi.data.model.users.UsersItemModel
import com.example.jsonplaceholderapi.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    val repository: Repository
): ViewModel() {

    val _photos = MutableStateFlow(ArrayList<PhotosItemModel>(emptyList()))
    var photos : StateFlow<ArrayList<PhotosItemModel>> = _photos

    init {
        viewModelScope.launch{
            _photos.value= repository.getPhotos()
        }
    }
}
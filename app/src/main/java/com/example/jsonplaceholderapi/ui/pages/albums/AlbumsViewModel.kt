package com.example.jsonplaceholderapi.ui.pages.albums

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jsonplaceholderapi.data.model.albums.AlbumsItemModel
import com.example.jsonplaceholderapi.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(
    val repository: Repository
): ViewModel() {

    val _albums = MutableStateFlow(ArrayList<AlbumsItemModel>(emptyList()))
    var albums : StateFlow<ArrayList<AlbumsItemModel>> = _albums

    init {
        viewModelScope.launch{
            _albums.value= repository.getAlbums()
        }
    }
}
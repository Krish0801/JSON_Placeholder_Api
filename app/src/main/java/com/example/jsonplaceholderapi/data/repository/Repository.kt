package com.example.jsonplaceholderapi.data.repository

import com.example.jsonplaceholderapi.data.model.albums.AlbumsItemModel
import com.example.jsonplaceholderapi.data.model.comments.CommentsItemModel
import com.example.jsonplaceholderapi.data.model.photos.PhotosItemModel
import com.example.jsonplaceholderapi.data.model.posts.PostsItemModel
import com.example.jsonplaceholderapi.data.model.todos.TodosItemModel
import com.example.jsonplaceholderapi.data.model.users.UsersItemModel
import com.example.jsonplaceholderapi.util.Resource
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getPosts(): ArrayList<PostsItemModel>

    suspend fun getAlbums(): ArrayList<AlbumsItemModel>

    suspend fun getComments(): ArrayList<CommentsItemModel>

    suspend fun getPhotos(): ArrayList<PhotosItemModel>

    suspend fun getTodos(): ArrayList<TodosItemModel>

    suspend fun getUsers(): ArrayList<UsersItemModel>

    fun loginUser(email:String, password:String): Flow<Resource<AuthResult>>

    fun registerUser(email:String, password:String): Flow<Resource<AuthResult>>


}
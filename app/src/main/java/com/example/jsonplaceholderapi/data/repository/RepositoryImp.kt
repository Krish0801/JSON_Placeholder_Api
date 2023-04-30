package com.example.jsonplaceholderapi.data.repository

import com.example.jsonplaceholderapi.data.model.albums.AlbumsItemModel
import com.example.jsonplaceholderapi.data.model.comments.CommentsItemModel
import com.example.jsonplaceholderapi.data.model.photos.PhotosItemModel
import com.example.jsonplaceholderapi.data.model.posts.PostsItemModel
import com.example.jsonplaceholderapi.data.model.todos.TodosItemModel
import com.example.jsonplaceholderapi.data.model.users.UsersItemModel
import com.example.jsonplaceholderapi.data.remote.ApiRequest
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    val apiRequest: ApiRequest
): Repository {

    override suspend fun getAlbums() = apiRequest.getAlbums()

    override suspend fun getPosts()= apiRequest.getPosts()

    override suspend fun getComments() = apiRequest.getComments()

    override suspend fun getPhotos()= apiRequest.getPhotos()

    override suspend fun getTodos() = apiRequest.getTodos()

    override suspend fun getUsers() = apiRequest.getUsers()


}
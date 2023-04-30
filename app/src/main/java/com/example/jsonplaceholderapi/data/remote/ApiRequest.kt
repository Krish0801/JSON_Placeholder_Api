package com.example.jsonplaceholderapi.data.remote

import com.example.jsonplaceholderapi.data.model.albums.AlbumsItemModel
import com.example.jsonplaceholderapi.data.model.comments.CommentsItemModel
import com.example.jsonplaceholderapi.data.model.photos.PhotosItemModel
import com.example.jsonplaceholderapi.data.model.posts.PostsItemModel
import com.example.jsonplaceholderapi.data.model.todos.TodosItemModel
import com.example.jsonplaceholderapi.data.model.users.UsersItemModel
import retrofit2.http.GET

interface ApiRequest {

    @GET(ApiDetails.POSTS_URL)
    suspend fun getPosts(): ArrayList<PostsItemModel>

    @GET(ApiDetails.COMMENTS_URL)
    suspend fun getComments(): ArrayList<CommentsItemModel>

    @GET(ApiDetails.ALBUMS_URL)
    suspend fun getAlbums(): ArrayList<AlbumsItemModel>

    @GET(ApiDetails.PHOTOS_URL)
    suspend fun getPhotos(): ArrayList<PhotosItemModel>

    @GET(ApiDetails.USERS_URL)
    suspend fun getUsers(): ArrayList<UsersItemModel>

    @GET(ApiDetails.TODOS_URL)
    suspend fun getTodos(): ArrayList<TodosItemModel>
}
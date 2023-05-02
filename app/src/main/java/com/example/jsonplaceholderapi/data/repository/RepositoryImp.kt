package com.example.jsonplaceholderapi.data.repository

import com.example.jsonplaceholderapi.data.model.albums.AlbumsItemModel
import com.example.jsonplaceholderapi.data.model.comments.CommentsItemModel
import com.example.jsonplaceholderapi.data.model.photos.PhotosItemModel
import com.example.jsonplaceholderapi.data.model.posts.PostsItemModel
import com.example.jsonplaceholderapi.data.model.todos.TodosItemModel
import com.example.jsonplaceholderapi.data.model.users.UsersItemModel
import com.example.jsonplaceholderapi.data.remote.ApiRequest
import com.example.jsonplaceholderapi.util.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    val apiRequest: ApiRequest,
    private val firebaseAuth: FirebaseAuth
) : Repository {

    override suspend fun getAlbums() = apiRequest.getAlbums()

    override suspend fun getPosts() = apiRequest.getPosts()

    override suspend fun getComments() = apiRequest.getComments()

    override suspend fun getPhotos() = apiRequest.getPhotos()

    override suspend fun getTodos() = apiRequest.getTodos()

    override suspend fun getUsers() = apiRequest.getUsers()

    override fun loginUser(email: String, password: String): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Loading())
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            emit(Resource.Success(result))
        }.catch {
            emit(Resource.Error(it.message.toString()))
        }
    }

    override fun registerUser(email: String, password: String): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Loading())
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            emit(Resource.Success(result))
        }.catch {
            emit(Resource.Error(it.message.toString()))
        }
    }


}
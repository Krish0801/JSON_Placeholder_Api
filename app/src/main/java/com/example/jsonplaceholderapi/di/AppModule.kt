package com.example.jsonplaceholderapi.di

import android.content.Context
import androidx.navigation.NavHostController
import com.example.jsonplaceholderapi.Application
import com.example.jsonplaceholderapi.data.remote.ApiDetails
import com.example.jsonplaceholderapi.data.remote.ApiRequest
import com.example.jsonplaceholderapi.data.repository.Repository
import com.example.jsonplaceholderapi.data.repository.RepositoryImp
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    fun provideOkhttp(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
            .build()
    }

    @Provides
    fun provideRetrofit(
        gson: Gson,
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiDetails.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    @Provides
    fun provideAPI(retrofit: Retrofit): ApiRequest {
        return retrofit.create(ApiRequest::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(apiRequest: ApiRequest, firebaseAuth: FirebaseAuth): Repository {
        return RepositoryImp(apiRequest, firebaseAuth)
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth() = FirebaseAuth . getInstance()

    @Provides
    @Singleton
    fun provideNavHostController(application: Application): NavHostController {
        return NavHostController(application)
    }

    @Provides
    @Singleton
    fun provideApplication(@ApplicationContext context: Context): Application {
        return context.applicationContext as Application
    }


}

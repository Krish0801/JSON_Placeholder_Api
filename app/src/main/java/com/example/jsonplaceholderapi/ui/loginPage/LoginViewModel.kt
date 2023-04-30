package com.example.jsonplaceholderapi.ui.loginPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.jsonplaceholderapi.data.repository.AuthRepository
import com.example.jsonplaceholderapi.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.jsonplaceholderapi.util.Resource.Success
import com.example.jsonplaceholderapi.util.Resource.Error
import com.example.jsonplaceholderapi.util.Resource.Loading


@HiltViewModel
class LoginViewModel @Inject constructor(
    val repository: AuthRepository
) : ViewModel() {

    val _loginState = Channel<LoginState>()
    val loginState = _loginState.receiveAsFlow()

    fun loginUser(email: String, password: String) = viewModelScope.launch {
        repository.loginUser(email, password).collect { result ->
            when (result) {
                is Success -> {
                    _loginState.send(LoginState(isSuccess = "Sign In Success"))

                }

                is Loading -> {
                    _loginState.send(LoginState(isLoading = true))

                }

                is Error -> {
                    _loginState.send(LoginState(isError = result.message))

                }

            }

        }
    }
}
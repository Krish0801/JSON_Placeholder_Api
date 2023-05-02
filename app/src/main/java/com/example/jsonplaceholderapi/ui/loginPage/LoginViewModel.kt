package com.example.jsonplaceholderapi.ui.loginPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jsonplaceholderapi.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.jsonplaceholderapi.util.Resource


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _loginState = Channel<LoginState>()
    val loginState = _loginState.receiveAsFlow()

    fun loginUser(email: String, password: String) = viewModelScope.launch {
        repository.loginUser(email, password).collect { result ->

            when (result) {
                is Resource.Success -> {
                    _loginState.send(LoginState(isSuccess = "Sign In Success"))

                }
                is Resource.Loading -> {
                    _loginState.send(LoginState(isLoading = true))

                }
                is Resource.Error -> {
                    _loginState.send(LoginState(isError = result.message))

                }
            }

        }
    }


}
package com.example.jsonplaceholderapi.ui.registerPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jsonplaceholderapi.data.repository.AuthRepository
import com.example.jsonplaceholderapi.util.Resource.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    val repository: AuthRepository,
) : ViewModel() {

    val _registerState = Channel<RegisterState>()
    val registerState = _registerState.receiveAsFlow()

    fun registerUser(email: String, password: String) = viewModelScope.launch {
        repository.loginUser(email, password).collect { result ->
            when (result) {
                is Success -> {
                    _registerState.send(RegisterState(isSuccess = "Sign In Success"))

                }

                is Loading -> {
                    _registerState.send(RegisterState(isLoading = true))

                }

                is Error -> {
                    _registerState.send(RegisterState(isError = result.message))

                }

            }

        }
    }
}
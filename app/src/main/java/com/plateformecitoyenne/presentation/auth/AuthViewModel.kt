package com.plateformecitoyenne.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plateformecitoyenne.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val repository = AuthRepository()

    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> = _state

    fun login(email: String, password: String) {

        viewModelScope.launch {

            _state.value = AuthState(isLoading = true)

            val result = repository.login(email, password)

            if (result.isSuccess) {

                _state.value = AuthState(isSuccess = true)

            } else {

                val erreur = result.exceptionOrNull()

                erreur?.printStackTrace()

                _state.value = AuthState(
                    error = erreur?.localizedMessage ?: "Erreur inconnue"
                )
            }
        }
    }

    fun register(email: String, password: String) {

        viewModelScope.launch {

            _state.value = AuthState(isLoading = true)

            val result = repository.register(email, password)

            if (result.isSuccess) {

                _state.value = AuthState(isSuccess = true)

            } else {

                _state.value = AuthState(
                    error = result.exceptionOrNull()?.message
                )
            }
        }
    }

    fun logout() {
        repository.logout()
    }
}
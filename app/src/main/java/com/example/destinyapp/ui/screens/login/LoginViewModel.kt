package com.example.destinyapp.ui.screens.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LoginState())
    val uiState: StateFlow<LoginState> = _uiState.asStateFlow()

    fun onEmailChanged(email: String) {
        _uiState.update { it.copy(email = email, emailError = null) }
    }

    fun onPasswordChanged(password: String) {
        _uiState.update { it.copy(password = password, passwordError = null) }
    }

    fun onLoginClicked(onSuccess: () -> Unit) {
        val currentState = _uiState.value
        var hasError = false

        if (currentState.email.isBlank()) {
            _uiState.update { it.copy(emailError = "El correo es obligatorio") }
            hasError = true
        }

        if (currentState.password.isBlank()) {
            _uiState.update { it.copy(passwordError = "La contraseña es obligatoria") }
            hasError = true
        }

        if (!hasError) {
            _uiState.update { it.copy(isLoading = true, loginError = null) }
            // Simular login
            onSuccess()
        }
    }
}

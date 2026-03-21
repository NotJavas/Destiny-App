package com.example.destinyapp.ui.screens.register

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(RegisterState())
    val uiState: StateFlow<RegisterState> = _uiState.asStateFlow()

    fun onNameChanged(name: String) {
        _uiState.update { it.copy(name = name, nameError = null) }
    }

    fun onEmailChanged(email: String) {
        _uiState.update { it.copy(email = email, emailError = null) }
    }

    fun onPasswordChanged(password: String) {
        _uiState.update { it.copy(password = password, passwordError = null) }
    }

    fun onConfirmPasswordChanged(password: String) {
        _uiState.update { it.copy(confirmPassword = password, confirmPasswordError = null) }
    }

    fun onRegisterClicked(onSuccess: () -> Unit) {
        val currentState = _uiState.value
        var hasError = false

        if (currentState.name.isBlank()) {
            _uiState.update { it.copy(nameError = "El nombre es obligatorio") }
            hasError = true
        }

        if (currentState.email.isBlank()) {
            _uiState.update { it.copy(emailError = "El correo es obligatorio") }
            hasError = true
        }

        if (currentState.password.length < 6) {
            _uiState.update { it.copy(passwordError = "Mínimo 6 caracteres") }
            hasError = true
        }

        if (currentState.password != currentState.confirmPassword) {
            _uiState.update { it.copy(confirmPasswordError = "Las contraseñas no coinciden") }
            hasError = true
        }

        if (!hasError) {
            _uiState.update { it.copy(isLoading = true, registerError = null) }
            // Simular registro
            onSuccess()
        }
    }
}

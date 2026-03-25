package com.example.destinyapp.ui.screens.profile

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProfileViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileState())
    val uiState: StateFlow<ProfileState> = _uiState.asStateFlow()

    fun toggleEditMode() {
        _uiState.update { it.copy(isEditing = !it.isEditing) }
    }

    fun onNameChanged(name: String) {
        _uiState.update { it.copy(name = name) }
    }

    fun onEmailChanged(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    fun onBirthDateChanged(date: String) {
        _uiState.update { it.copy(birthDate = date) }
    }

    fun onGenderChanged(gender: String) {
        _uiState.update { it.copy(gender = gender) }
    }

    fun toggleTag(tag: String) {
        _uiState.update { state ->
            val newTags = if (state.selectedTags.contains(tag)) {
                state.selectedTags - tag
            } else {
                state.selectedTags + tag
            }
            state.copy(selectedTags = newTags)
        }
    }

    fun saveProfile() {
        _uiState.update { it.copy(isEditing = false) }
    }
}

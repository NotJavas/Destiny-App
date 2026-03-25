package com.example.destinyapp.ui.screens.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeState())
    val uiState: StateFlow<HomeState> = _uiState.asStateFlow()

    init {
        loadEvents()
    }

    private fun loadEvents() {
        val events = listOf(
            HomeEvent(
                id = "1",
                title = "Neon Nights Festival",
                description = "La fiesta electrónica más grande del año. DJs internacionales y show de luces.",
                tag = "Trending"
            ),
            HomeEvent(
                id = "2",
                title = "Techno & Wine",
                description = "Una experiencia sensorial única en los viñedos de Querétaro.",
                tag = "Exclusivo"
            ),
            HomeEvent(
                id = "3",
                title = "Sky Bar Polanco",
                description = "Noche de coctelería de autor con la mejor vista de la ciudad.",
                tag = "Popular"
            ),
            HomeEvent(
                id = "4",
                title = "Indie Rock Night",
                description = "Bandas emergentes locales en un ambiente íntimo y vibrante.",
                tag = "Nuevo"
            )
        )
        _uiState.value = HomeState(featuredEvents = events)
    }
}

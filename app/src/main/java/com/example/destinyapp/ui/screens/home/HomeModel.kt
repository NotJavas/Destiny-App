package com.example.destinyapp.ui.screens.home

import androidx.compose.ui.graphics.Brush

data class HomeEvent(
    val id: String,
    val title: String,
    val description: String,
    val tag: String = "Trending",
    val imageUrl: String? = null,
    val customGradient: Brush? = null
)

data class HomeState(
    val featuredEvents: List<HomeEvent> = emptyList(),
    val isLoading: Boolean = false
)

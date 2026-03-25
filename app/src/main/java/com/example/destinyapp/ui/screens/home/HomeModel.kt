package com.example.destinyapp.ui.screens.home

data class HomeEvent(
    val id: String,
    val title: String,
    val description: String,
    val tag: String = "Trending",
    val imageUrl: String? = null
)

data class HomeState(
    val featuredEvents: List<HomeEvent> = emptyList(),
    val isLoading: Boolean = false
)

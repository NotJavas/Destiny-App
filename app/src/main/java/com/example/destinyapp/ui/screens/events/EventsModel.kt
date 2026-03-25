package com.example.destinyapp.ui.screens.events

data class DetailedEvent(
    val id: String,
    val title: String,
    val category: String,
    val tags: List<String>,
    val date: String,
    val location: String,
    val price: String
)

data class EventsState(
    val events: List<DetailedEvent> = emptyList(),
    val isLoading: Boolean = false
)

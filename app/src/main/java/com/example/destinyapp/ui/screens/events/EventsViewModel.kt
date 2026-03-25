package com.example.destinyapp.ui.screens.events

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class EventsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(EventsState())
    val uiState: StateFlow<EventsState> = _uiState.asStateFlow()

    init {
        loadDetailedEvents()
    }

    private fun loadDetailedEvents() {
        val detailedEvents = listOf(
            DetailedEvent(
                id = "1",
                title = "Neon Nights Festival",
                category = "Música Electrónica",
                tags = listOf("Techno", "Outdoor", "Premium"),
                date = "24 Oct, 2024",
                location = "Querétaro, Qro.",
                price = "$450 MXN"
            ),
            DetailedEvent(
                id = "2",
                title = "Techno & Wine Experience",
                category = "Cultura & Social",
                tags = listOf("Mixología", "Viñedo", "Exclusive"),
                date = "15 Nov, 2024",
                location = "Tequisquiapan, Qro.",
                price = "$1,200 MXN"
            ),
            DetailedEvent(
                id = "3",
                title = "Sky Bar Polanco: Grand Opening",
                category = "Vida Nocturna",
                tags = listOf("Rooftop", "Networking", "VIP"),
                date = "02 Dic, 2024",
                location = "Polanco, CDMX",
                price = "$600 MXN"
            ),
            DetailedEvent(
                id = "4",
                title = "Indie Rock Local Showcase",
                category = "Música en Vivo",
                tags = listOf("Concierto", "Underground", "Bebidas"),
                date = "10 Dic, 2024",
                location = "Centro, Querétaro",
                price = "$150 MXN"
            )
        )
        _uiState.value = EventsState(events = detailedEvents)
    }
}

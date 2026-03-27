package com.example.destinyapp.ui.screens.home

import androidx.lifecycle.ViewModel
import com.example.destinyapp.ui.resources.DestinyGradients
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
                tag = "Trending",
                customGradient = DestinyGradients.DestinyPrimary
            ),
            HomeEvent(
                id = "2",
                title = "Techno & Wine",
                description = "Una experiencia sensorial única en los viñedos de Querétaro.",
                tag = "Exclusivo",
                customGradient = DestinyGradients.NeonAurora
            ),
            HomeEvent(
                id = "3",
                title = "Sky Bar Opening",
                description = "Noche de coctelería de autor con la mejor vista de la ciudad.",
                tag = "Popular",
                customGradient = DestinyGradients.CyberPunk
            ),
            HomeEvent(
                id = "4",
                title = "Deep Space Experience",
                description = "Inmersión astronómica con telescopios profesionales y guías expertos.",
                tag = "Nuevo",
                customGradient = DestinyGradients.DeepOcean
            ),
            HomeEvent(
                id = "5",
                title = "Sunset Mars Party",
                description = "Celebra el atardecer con ritmos tribales y una atmósfera volcánica.",
                tag = "Caliente",
                customGradient = DestinyGradients.SunsetMars
            ),
            HomeEvent(
                id = "6",
                title = "Toxic Lime Club",
                description = "Energía pura en la pista de baile con visuales radioactivos.",
                tag = "Energía",
                customGradient = DestinyGradients.ToxicLime
            ),
            HomeEvent(
                id = "7",
                title = "Royal Gold Gala",
                description = "Una noche de elegancia y exclusividad para los miembros VIP.",
                tag = "VIP",
                customGradient = DestinyGradients.RoyalGold
            ),
            HomeEvent(
                id = "8",
                title = "Midnight Jazz",
                description = "Relájate con las mejores notas de jazz en un ambiente íntimo.",
                tag = "Relajante",
                customGradient = DestinyGradients.Midnight
            ),
            HomeEvent(
                id = "9",
                title = "Mystic Teal Retreat",
                description = "Conecta con tu interior en este retiro espiritual junto al lago.",
                tag = "Zen",
                customGradient = DestinyGradients.MysticTeal
            ),
            HomeEvent(
                id = "10",
                title = "Electric Violet Rave",
                description = "Siente la corriente con el techno más potente de la escena.",
                tag = "Vibrante",
                customGradient = DestinyGradients.ElectricViolet
            ),
            HomeEvent(
                id = "11",
                title = "Solar Flare Lounge",
                description = "Disfruta de la mejor música chill-out bajo el sol radiante.",
                tag = "Chill",
                customGradient = DestinyGradients.SolarFlare
            ),
            HomeEvent(
                id = "12",
                title = "Berry Smoothie Fair",
                description = "Festival de sabores naturales y música acústica al aire libre.",
                tag = "Natural",
                customGradient = DestinyGradients.BerrySmoothie
            ),
            HomeEvent(
                id = "13",
                title = "Arctic Ice Skating",
                description = "Patinaje sobre hielo con efectos visuales polares impresionantes.",
                tag = "Fresco",
                customGradient = DestinyGradients.ArcticIce
            )
        )
        _uiState.value = HomeState(featuredEvents = events)
    }
}

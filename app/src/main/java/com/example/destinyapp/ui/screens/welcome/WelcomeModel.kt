package com.example.destinyapp.ui.screens.welcome

/**
 * Representa el estado de la pantalla de bienvenida.
 */
data class WelcomeState(
    val title: String = "Descubre tu próximo destino",
    val subtitle: String = "Explora los mejores eventos y lugares exclusivos cerca de ti con la experiencia Destiny.",
    val isLoading: Boolean = false
)

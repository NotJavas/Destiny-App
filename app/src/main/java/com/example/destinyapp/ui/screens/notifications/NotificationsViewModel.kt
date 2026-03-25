package com.example.destinyapp.ui.screens.notifications

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Celebration
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NotificationsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(NotificationsState())
    val uiState: StateFlow<NotificationsState> = _uiState.asStateFlow()

    init {
        loadNotifications()
    }

    private fun loadNotifications() {
        val list = listOf(
            DestinyNotification(
                id = "1",
                title = "¡Nuevo evento cerca!",
                message = "El Neon Nights Festival comienza en 2 horas en Plaza Querétaro. ¡No te lo pierdas!",
                time = "Hace 5 min",
                icon = Icons.Default.NotificationsActive,
                isUnread = true
            ),
            DestinyNotification(
                id = "2",
                title = "Boleto Confirmado",
                message = "Tu acceso para 'Techno & Wine' ha sido generado exitosamente. Revisa tu correo.",
                time = "Hace 1 hora",
                icon = Icons.Default.ConfirmationNumber,
                isUnread = true
            ),
            DestinyNotification(
                id = "3",
                title = "Zona en Tendencia",
                message = "Sky Bar Polanco tiene una afluencia alta hoy. ¡Es el lugar del momento!",
                time = "Hace 3 horas",
                icon = Icons.Default.LocalFireDepartment,
                isUnread = false
            ),
            DestinyNotification(
                id = "4",
                title = "¡Felicidades!",
                message = "Has alcanzado el nivel 'Explorer Pro'. Sigue explorando para ganar más puntos.",
                time = "Ayer",
                icon = Icons.Default.Celebration,
                isUnread = false
            )
        )
        _uiState.update { it.copy(notifications = list) }
    }

    fun markAsRead(id: String) {
        _uiState.update { state ->
            val newList = state.notifications.map {
                if (it.id == id) it.copy(isUnread = false) else it
            }
            state.copy(notifications = newList)
        }
    }
}

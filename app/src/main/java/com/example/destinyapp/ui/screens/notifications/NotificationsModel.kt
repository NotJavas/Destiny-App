package com.example.destinyapp.ui.screens.notifications

import androidx.compose.ui.graphics.vector.ImageVector

data class DestinyNotification(
    val id: String,
    val title: String,
    val message: String,
    val time: String,
    val icon: ImageVector,
    val isUnread: Boolean = false
)

data class NotificationsState(
    val notifications: List<DestinyNotification> = emptyList(),
    val isLoading: Boolean = false
)

package com.example.destinyapp.ui.screens.events

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.destinyapp.ui.components.DestinyDetailedEventCard
import com.example.destinyapp.ui.theme.DestinyAppTheme

@Composable
fun EventsScreen(
    viewModel: EventsViewModel = viewModel(),
    onEventClick: (String) -> Unit = {}
) {
    val state by viewModel.uiState.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 24.dp),
        contentPadding = PaddingValues(top = 24.dp, bottom = 100.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {
            Text(
                text = "Eventos Próximos",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        items(state.events) { event ->
            DestinyDetailedEventCard(
                title = event.title,
                category = event.category,
                tags = event.tags,
                date = event.date,
                location = event.location,
                price = event.price,
                onActionClick = { onEventClick(event.id) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EventsScreenPreview() {
    DestinyAppTheme(darkTheme = true) {
        EventsScreen()
    }
}

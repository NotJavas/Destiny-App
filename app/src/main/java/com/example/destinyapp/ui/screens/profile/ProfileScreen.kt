package com.example.destinyapp.ui.screens.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.destinyapp.ui.components.DestinyGradientButton
import com.example.destinyapp.ui.components.DestinyProfileCard
import com.example.destinyapp.ui.components.DestinyTextInput
import com.example.destinyapp.ui.resources.*
import com.example.destinyapp.ui.theme.DestinyAppTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = viewModel(),
    onLogout: () -> Unit = {}
) {
    val state by viewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
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
                    text = "Mi Perfil",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Black
                )
            }

            item {
                DestinyProfileCard(
                    name = state.name,
                    email = state.email
                )
            }

            item {
                Text(
                    text = "Información Personal",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = DestinyPurple
                )
            }

            item {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    DestinyTextInput(
                        value = state.name,
                        onValueChange = viewModel::onNameChanged,
                        label = "Nombre",
                        leadingIcon = Icons.Default.Person
                    )
                    DestinyTextInput(
                        value = state.email,
                        onValueChange = viewModel::onEmailChanged,
                        label = "Email",
                        leadingIcon = Icons.Default.Email
                    )
                    DestinyTextInput(
                        value = state.birthDate,
                        onValueChange = viewModel::onBirthDateChanged,
                        label = "Fecha de Nacimiento",
                        leadingIcon = Icons.Default.Cake,
                        placeholder = "AAAA-MM-DD"
                    )
                    
                    // Selector de Género Simple
                    Column {
                        Text(
                            text = "Género",
                            style = MaterialTheme.typography.labelLarge,
                            color = DestinyNeutral600,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            listOf("Masculino", "Femenino", "Otro").forEach { gender ->
                                val isSelected = state.gender == gender
                                FilterChip(
                                    selected = isSelected,
                                    onClick = { viewModel.onGenderChanged(gender) },
                                    label = { Text(gender) },
                                    colors = FilterChipDefaults.filterChipColors(
                                        selectedContainerColor = DestinyPurple.copy(alpha = 0.2f),
                                        selectedLabelColor = DestinyPurple
                                    )
                                )
                            }
                        }
                    }
                }
            }

            item {
                Text(
                    text = "Mis Intereses",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = DestinyPurple
                )
                Text(
                    text = "Selecciona lo que más te gusta para personalizar tu experiencia",
                    style = MaterialTheme.typography.bodySmall,
                    color = DestinyNeutral600
                )
            }

            // Categorías de Intereses
            INTEREST_TAGS.forEach { (category, tags) ->
                item {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = category,
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                        FlowRow(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            tags.forEach { tag ->
                                InterestTag(
                                    text = tag,
                                    isSelected = state.selectedTags.contains(tag),
                                    onClick = { viewModel.toggleTag(tag) }
                                )
                            }
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedButton(
                    onClick = onLogout,
                    modifier = Modifier.fillMaxWidth(),
                    border = BorderStroke(1.dp, DestinyRed.copy(alpha = 0.5f)),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = DestinyRed)
                ) {
                    Icon(Icons.Default.Logout, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Cerrar Sesión")
                }
            }
        }

        // Botón Flotante de Guardar (Visual)
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(24.dp)
        ) {
            DestinyGradientButton(
                text = "Guardar Cambios",
                modifier = Modifier.fillMaxWidth(),
                onClick = { viewModel.saveProfile() }
            )
        }
    }
}

@Composable
fun InterestTag(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier.clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        color = if (isSelected) DestinyPurple.copy(alpha = 0.1f) else Color.Transparent,
        border = BorderStroke(
            width = 1.dp,
            color = if (isSelected) DestinyPurple else MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
        )
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            style = MaterialTheme.typography.bodySmall,
            color = if (isSelected) DestinyPurple else MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    DestinyAppTheme(darkTheme = true) {
        ProfileScreen()
    }
}

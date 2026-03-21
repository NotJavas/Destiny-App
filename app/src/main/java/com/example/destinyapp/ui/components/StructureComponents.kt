package com.example.destinyapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.destinyapp.ui.resources.*
import com.example.destinyapp.ui.theme.DestinyAppTheme

/**
 * Componente de navegación inferior con efecto de vidrio (Glassmorphism).
 */
@Composable
fun DestinyBottomBar(
    selectedRoute: String,
    onNavigate: (String) -> Unit
) {
    NavigationBar(
        containerColor = DestinyGlassBlack,
        tonalElevation = 0.dp
    ) {
        val items = listOf("Home", "Map", "Tickets", "Notifications", "Profile")
        items.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(Icons.Default.Info, contentDescription = screen) },
                label = { Text(screen, style = MaterialTheme.typography.labelSmall) },
                selected = selectedRoute == screen,
                onClick = { onNavigate(screen) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = DestinyPurple,
                    unselectedIconColor = DestinyNeutral600,
                    indicatorColor = Color.Transparent,
                    selectedTextColor = DestinyPurple,
                    unselectedTextColor = DestinyNeutral600
                )
            )
        }
    }
}

/**
 * Componente de pestañas (Tabs) estilo Material3.
 */
@Composable
fun DestinyTabs() {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("Próximos", "Pasados", "Guardados")

    TabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = MaterialTheme.colorScheme.surface,
        indicator = { tabPositions ->
            if (selectedTabIndex < tabPositions.size) {
                TabRowDefaults.PrimaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    color = DestinyPurple
                )
            }
        },
        divider = {}
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = { selectedTabIndex = index },
                text = { 
                    Text(
                        text = title, 
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Normal
                    ) 
                },
                selectedContentColor = MaterialTheme.colorScheme.onSurface,
                unselectedContentColor = DestinyNeutral600
            )
        }
    }
}

/**
 * Elemento de lista estándar.
 */
@Composable
fun DestinyListItem(
    headline: String,
    supportingText: String? = null,
    leadingIcon: ImageVector? = null,
    onClick: () -> Unit = {}
) {
    ListItem(
        headlineContent = { 
            Text(
                text = headline, 
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold
            ) 
        },
        supportingContent = supportingText?.let { { 
            Text(
                text = it, 
                color = DestinyNeutral600,
                style = MaterialTheme.typography.bodyMedium
            ) 
        } },
        leadingContent = leadingIcon?.let { { 
            Icon(it, contentDescription = null, tint = DestinyPurple) 
        } },
        trailingContent = { 
            Icon(Icons.Default.PlayArrow, contentDescription = null, tint = DestinyNeutral600) 
        },
        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
        modifier = Modifier.background(MaterialTheme.colorScheme.surface)
    )
}

/**
 * Diálogo de confirmación.
 */
@Composable
fun ExitDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { 
            Text(
                text = "¿Salir del evento?", 
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.headlineSmall
            ) 
        },
        text = { 
            Text(
                text = "Perderás tu lugar en la fila virtual si sales ahora.", 
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyMedium
            ) 
        },
        confirmButton = {
            Button(
                onClick = onConfirm,
                colors = ButtonDefaults.buttonColors(containerColor = DestinyRed)
            ) {
                Text("Salir")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar", color = DestinyNeutral600)
            }
        },
        containerColor = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(16.dp)
    )
}

/**
 * Placeholder para estados de carga (Skeleton).
 */
@Composable
fun SkeletonCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(12.dp))
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF181818)
@Composable
fun StructurePreview() {
    DestinyAppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            DestinyTabs()
            DestinyListItem(headline = "Configuración de Cuenta", supportingText = "Email, Seguridad")
            SkeletonCard()
        }
    }
}

package com.example.destinyapp.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.destinyapp.ui.components.DestinyBottomBar
import com.example.destinyapp.ui.screens.events.EventsScreen
import com.example.destinyapp.ui.screens.home.HomeScreen
import com.example.destinyapp.ui.screens.login.LoginScreen
import com.example.destinyapp.ui.screens.map.MapScreen
import com.example.destinyapp.ui.screens.notifications.NotificationsScreen
import com.example.destinyapp.ui.screens.profile.ProfileScreen
import com.example.destinyapp.ui.screens.register.RegisterScreen
import com.example.destinyapp.ui.screens.welcome.WelcomeScreen
import com.example.destinyapp.ui.theme.DestinyAppTheme

sealed class Screen(
    val route: String,
    val title: String = "",
    val filledIcon: ImageVector? = null,
    val outlinedIcon: ImageVector? = null,
    val index: Int = -1
) {
    // Auth Screens
    object Welcome : Screen("welcome")
    object Login : Screen("login")
    object Register : Screen("register")

    // Main App Screens (Index defines slide direction)
    object Home : Screen("home", "Inicio", Icons.Filled.Home, Icons.Outlined.Home, 0)
    object Events : Screen("events", "Eventos", Icons.Filled.Event, Icons.Outlined.Event, 1)
    object Map : Screen("map", "Mapa", Icons.Filled.Map, Icons.Outlined.Map, 2)
    object Notifications : Screen("notifications", "Alertas", Icons.Filled.Notifications, Icons.Outlined.Notifications, 3)
    object Profile : Screen("profile", "Perfil", Icons.Filled.Person, Icons.Outlined.Person, 4)
}

/**
 * Determina si la transición debe ser hacia la izquierda o derecha basado en el índice de la pantalla.
 */
private fun getSlideDirection(
    initial: NavBackStackEntry,
    target: NavBackStackEntry
): AnimatedContentTransitionScope.SlideDirection {
    val initialRoute = initial.destination.route
    val targetRoute = target.destination.route

    val screens = listOf(
        Screen.Home, Screen.Events, Screen.Map, Screen.Notifications, Screen.Profile
    )

    val initialIndex = screens.find { it.route == initialRoute }?.index ?: -1
    val targetIndex = screens.find { it.route == targetRoute }?.index ?: -1

    return if (targetIndex > initialIndex) {
        AnimatedContentTransitionScope.SlideDirection.Left
    } else {
        AnimatedContentTransitionScope.SlideDirection.Right
    }
}

@Composable
fun DestinyNavHost() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val mainScreens = listOf(
        Screen.Home.route,
        Screen.Events.route,
        Screen.Map.route,
        Screen.Notifications.route,
        Screen.Profile.route
    )

    Scaffold(
        bottomBar = {
            if (currentRoute in mainScreens) {
                DestinyBottomBar(navController)
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Welcome.route,
            modifier = Modifier.padding(
                top = padding.calculateTopPadding(),
                bottom = 0.dp // Permite que el contenido se vea por detrás de la barra flotante
            ),
            enterTransition = {
                slideIntoContainer(
                    towards = getSlideDirection(initialState, targetState),
                    animationSpec = tween(500)
                ) + fadeIn(animationSpec = tween(500))
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = getSlideDirection(initialState, targetState),
                    animationSpec = tween(500)
                ) + fadeOut(animationSpec = tween(500))
            }
        ) {
            composable(Screen.Welcome.route) {
                WelcomeScreen(
                    onNavigateToLogin = { navController.navigate(Screen.Login.route) },
                    onNavigateToRegister = { navController.navigate(Screen.Register.route) }
                )
            }

            composable(Screen.Login.route) {
                LoginScreen(
                    onNavigateBack = { navController.popBackStack() },
                    onNavigateToRegister = { navController.navigate(Screen.Register.route) },
                    onLoginSuccess = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Welcome.route) { inclusive = true }
                        }
                    }
                )
            }

            composable(Screen.Register.route) {
                RegisterScreen(
                    onNavigateBack = { navController.popBackStack() },
                    onNavigateToLogin = {
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.Welcome.route)
                        }
                    },
                    onRegisterSuccess = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Welcome.route) { inclusive = true }
                        }
                    }
                )
            }

            // MAIN SCREENS
            composable(Screen.Home.route) { 
                HomeScreen(onEventClick = { /* Navegar a detalle */ }) 
            }
            
            composable(Screen.Events.route) { 
                EventsScreen(onEventClick = { /* Navegar a detalle */ }) 
            }
            
            composable(Screen.Map.route) { 
                MapScreen() 
            }
            
            composable(Screen.Notifications.route) { 
                NotificationsScreen() 
            }

            composable(Screen.Profile.route) {
                ProfileScreen(
                    onLogout = {
                        navController.navigate(Screen.Welcome.route) {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun PlaceholderScreen(text: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DestinyNavHostPreview() {
    DestinyAppTheme {
        DestinyNavHost()
    }
}

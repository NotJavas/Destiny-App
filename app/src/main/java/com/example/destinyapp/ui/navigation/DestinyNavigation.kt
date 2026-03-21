package com.example.destinyapp.ui.navigation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
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
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.destinyapp.ui.components.DestinyBottomBar
import com.example.destinyapp.ui.screens.login.LoginScreen
import com.example.destinyapp.ui.screens.register.RegisterScreen
import com.example.destinyapp.ui.screens.welcome.WelcomeScreen

sealed class Screen(
    val route: String, 
    val title: String = "", 
    val filledIcon: ImageVector? = null, 
    val outlinedIcon: ImageVector? = null
) {
    // Auth Screens
    object Welcome : Screen("welcome")
    object Login : Screen("login")
    object Register : Screen("register")
    
    // Main App Screens
    object Home : Screen("home", "Inicio", Icons.Filled.Home, Icons.Outlined.Home)
    object Events : Screen("events", "Eventos", Icons.Filled.Event, Icons.Outlined.Event)
    object Map : Screen("map", "Mapa", Icons.Filled.Map, Icons.Outlined.Map)
    object Notifications : Screen("notifications", "Alertas", Icons.Filled.Notifications, Icons.Outlined.Notifications)
    object Profile : Screen("profile", "Perfil", Icons.Filled.Person, Icons.Outlined.Person)
}

@Composable
fun DestinyNavHost() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Lista de rutas donde queremos mostrar la BottomBar
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
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Welcome.route,
            modifier = Modifier.padding(padding)
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

            // MAIN SCREENS CON ANIMACIÓN DE CARRUSEL SUTÍL
            composable(Screen.Home.route) { PlaceholderScreen("Explorar Destinos") }
            composable(Screen.Events.route) { PlaceholderScreen("Eventos Próximos") }
            composable(Screen.Map.route) { PlaceholderScreen("Mapa Interactivo") }
            composable(Screen.Notifications.route) { PlaceholderScreen("Notificaciones") }
            composable(Screen.Profile.route) { PlaceholderScreen("Ajustes de Perfil") }
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

package com.example.destinyapp.ui.screens.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.destinyapp.ui.components.*
import com.example.destinyapp.ui.resources.DestinyPurple
import com.example.destinyapp.ui.theme.DestinyAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = viewModel(),
    onNavigateBack: () -> Unit = {},
    onNavigateToLogin: () -> Unit = {},
    onRegisterSuccess: () -> Unit = {}
) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Regresar")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            DestinyPurple.copy(alpha = 0.1f),
                            MaterialTheme.colorScheme.background
                        )
                    )
                )
                .padding(padding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DestinyLogo(size = 90.dp)
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Text(
                    text = "Crea tu cuenta",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Black
                )
                
                Text(
                    text = "Únete a la comunidad de Destiny",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(32.dp))

                DestinyTextInput(
                    value = state.name,
                    onValueChange = viewModel::onNameChanged,
                    label = "Nombre completo",
                    leadingIcon = Icons.Default.Person,
                    isError = state.nameError != null,
                    errorMessage = state.nameError
                )

                Spacer(modifier = Modifier.height(16.dp))

                DestinyTextInput(
                    value = state.email,
                    onValueChange = viewModel::onEmailChanged,
                    label = "Correo electrónico",
                    leadingIcon = Icons.Default.Email,
                    isError = state.emailError != null,
                    errorMessage = state.emailError
                )

                Spacer(modifier = Modifier.height(16.dp))

                DestinyPasswordInput(
                    value = state.password,
                    onValueChange = viewModel::onPasswordChanged,
                    label = "Contraseña"
                )

                Spacer(modifier = Modifier.height(16.dp))

                DestinyPasswordInput(
                    value = state.confirmPassword,
                    onValueChange = viewModel::onConfirmPasswordChanged,
                    label = "Confirmar contraseña"
                )

                Spacer(modifier = Modifier.height(32.dp))

                DestinyGradientButton(
                    text = "Registrarse",
                    modifier = Modifier.fillMaxWidth(),
                    isLoading = state.isLoading,
                    onClick = { viewModel.onRegisterClicked(onRegisterSuccess) }
                )

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "¿Ya tienes cuenta?",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    DestinyTextButton(
                        text = "Inicia sesión",
                        onClick = onNavigateToLogin
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    DestinyAppTheme(darkTheme = true) {
        RegisterScreen()
    }
}

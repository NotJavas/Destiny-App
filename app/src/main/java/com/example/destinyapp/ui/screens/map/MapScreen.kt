package com.example.destinyapp.ui.screens.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.destinyapp.R
import com.example.destinyapp.ui.theme.DestinyAppTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.*

@Composable
fun MapScreen() {
    val context = LocalContext.current
    
    // Posición inicial: Querétaro Centro
    val queretaro = LatLng(20.5888, -100.3899)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(queretaro, 14f)
    }

    // Aplicar el estilo Dark personalizado que guardamos en raw
    val mapProperties = remember {
        MapProperties(
            mapStyleOptions = MapStyleOptions.loadRawResourceStyle(context, R.raw.map_style),
            isMyLocationEnabled = false
        )
    }

    val uiSettings = remember {
        MapUiSettings(
            zoomControlsEnabled = false,
            myLocationButtonEnabled = false,
            mapToolbarEnabled = false
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = mapProperties,
            uiSettings = uiSettings
        ) {
            // Marcadores de prueba usando rememberMarkerState para evitar recreaciones en recomposición
            Marker(
                state = rememberMarkerState(position = LatLng(20.5888, -100.3899)),
                title = "Neon Nights Festival",
                snippet = "Plaza de Armas"
            )
            
            Marker(
                state = rememberMarkerState(position = LatLng(20.5920, -100.3950)),
                title = "Indie Rock Local",
                snippet = "Centro Histórico"
            )

            Marker(
                state = rememberMarkerState(position = LatLng(20.5800, -100.3700)),
                title = "Sky Bar Opening",
                snippet = "Zona Álamos"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MapScreenPreview() {
    DestinyAppTheme(darkTheme = true) {
        MapScreen()
    }
}

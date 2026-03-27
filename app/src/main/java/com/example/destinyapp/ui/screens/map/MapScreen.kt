package com.example.destinyapp.ui.screens.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.destinyapp.R
import com.example.destinyapp.ui.components.DestinyMarker
import com.example.destinyapp.ui.components.MarkerAnimation
import com.example.destinyapp.ui.components.MarkerShape
import com.example.destinyapp.ui.resources.DestinyGradients
import com.example.destinyapp.ui.theme.DestinyAppTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.*

@Composable
fun MapScreen() {
    val context = LocalContext.current
    
    val queretaro = LatLng(20.5888, -100.3899)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(queretaro, 14f)
    }

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
            // 1. Destiny Primary - Pulse - Circle - Music
            MarkerComposable(state = rememberMarkerState(position = LatLng(20.5888, -100.3899))) {
                DestinyMarker(brush = DestinyGradients.DestinyPrimary, icon = Icons.Default.MusicNote, animationType = MarkerAnimation.Pulse)
            }
            
            // 2. Neon Aurora - Bounce - Square - Nightlife
            MarkerComposable(state = rememberMarkerState(position = LatLng(20.5920, -100.3950))) {
                DestinyMarker(brush = DestinyGradients.NeonAurora, icon = Icons.Default.Nightlife, animationType = MarkerAnimation.Bounce, shapeType = MarkerShape.Square)
            }

            // 3. Cyber Punk - Rotate - Diamond - Rocket
            MarkerComposable(state = rememberMarkerState(position = LatLng(20.5800, -100.3700))) {
                DestinyMarker(brush = DestinyGradients.CyberPunk, icon = Icons.Default.RocketLaunch, animationType = MarkerAnimation.Rotate, shapeType = MarkerShape.Diamond)
            }

            // 4. Sunset Mars - Pulse - Hexagon - LocalFireDepartment
            MarkerComposable(state = rememberMarkerState(position = LatLng(20.5850, -100.4000))) {
                DestinyMarker(brush = DestinyGradients.SunsetMars, icon = Icons.Default.LocalFireDepartment, animationType = MarkerAnimation.Pulse, shapeType = MarkerShape.Hexagon)
            }

            // 5. Electric Violet - Bounce - Circle - Celebration
            MarkerComposable(state = rememberMarkerState(position = LatLng(20.5950, -100.3850))) {
                DestinyMarker(brush = DestinyGradients.ElectricViolet, icon = Icons.Default.Celebration, animationType = MarkerAnimation.Bounce)
            }

            // 6. Toxic Lime - None - Square - SportsBar
            MarkerComposable(state = rememberMarkerState(position = LatLng(20.5750, -100.3900))) {
                DestinyMarker(brush = DestinyGradients.ToxicLime, icon = Icons.Default.SportsBar, shapeType = MarkerShape.Square)
            }

            // 7. Royal Gold - Rotate - Diamond - Star
            MarkerComposable(state = rememberMarkerState(position = LatLng(20.6000, -100.3800))) {
                DestinyMarker(brush = DestinyGradients.RoyalGold, icon = Icons.Default.Star, animationType = MarkerAnimation.Rotate, shapeType = MarkerShape.Diamond)
            }

            // 8. Deep Ocean - Bounce - Hexagon - DirectionsBoat
            MarkerComposable(state = rememberMarkerState(position = LatLng(20.5820, -100.4100))) {
                DestinyMarker(brush = DestinyGradients.DeepOcean, icon = Icons.Default.DirectionsBoat, animationType = MarkerAnimation.Bounce, shapeType = MarkerShape.Hexagon)
            }

            // 9. Mystic Teal - Pulse - Circle - Spa
            MarkerComposable(state = rememberMarkerState(position = LatLng(20.5700, -100.3750))) {
                DestinyMarker(brush = DestinyGradients.MysticTeal, icon = Icons.Default.Spa, animationType = MarkerAnimation.Pulse)
            }

            // 10. Solar Flare - None - Diamond - WbSunny
            MarkerComposable(state = rememberMarkerState(position = LatLng(20.6050, -100.4050))) {
                DestinyMarker(brush = DestinyGradients.SolarFlare, icon = Icons.Default.WbSunny, shapeType = MarkerShape.Diamond)
            }

            // 11. Berry Smoothie - Bounce - Square - Favorite
            MarkerComposable(state = rememberMarkerState(position = LatLng(20.5650, -100.3950))) {
                DestinyMarker(brush = DestinyGradients.BerrySmoothie, icon = Icons.Default.Favorite, animationType = MarkerAnimation.Bounce, shapeType = MarkerShape.Square)
            }

            // 12. Arctic Ice - Pulse - Circle - AcUnit
            MarkerComposable(state = rememberMarkerState(position = LatLng(20.6150, -100.3900))) {
                DestinyMarker(brush = DestinyGradients.ArcticIce, icon = Icons.Default.AcUnit, animationType = MarkerAnimation.Pulse)
            }

            // 13. Midnight - None - Hexagon - Bedtime
            MarkerComposable(state = rememberMarkerState(position = LatLng(20.5550, -100.3850))) {
                DestinyMarker(brush = DestinyGradients.Midnight, icon = Icons.Default.Bedtime, shapeType = MarkerShape.Hexagon)
            }
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

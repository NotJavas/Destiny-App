# Guía de Componentes de DestinyApp

Esta guía detalla cómo utilizar los componentes personalizados de la interfaz de usuario de DestinyApp.

---

## 🛡️ Marca (Logo)

### DestinyLogo
Componente que muestra el logo oficial de la marca con un degradado característico.
`DestinyLogo(modifier: Modifier = Modifier, size: Dp = 120.dp)`

**Ejemplo de uso:**
```kotlin
DestinyLogo(size = 80.dp)
```

---

## 🔘 Botones (Buttons.kt)

### DestinyGradientButton
Botón principal con degradado (Púrpura a Azul). Ideal para acciones principales.
`DestinyGradientButton(text: String, modifier: Modifier = Modifier, icon: ImageVector? = null, enabled: Boolean = true, isLoading: Boolean = false, size: DestinyButtonSize = DestinyButtonSize.NORMAL, onClick: () -> Unit)`

**Ejemplo de uso:**
```kotlin
DestinyGradientButton(
    text = "Comenzar ahora",
    onClick = { /* acción */ }
)
```

### DestinyOutlineButton
Botón secundario con borde. Se usa para acciones menos importantes.
`DestinyOutlineButton(text: String, modifier: Modifier = Modifier, enabled: Boolean = true, size: DestinyButtonSize = DestinyButtonSize.NORMAL, onClick: () -> Unit)`

**Ejemplo de uso:**
```kotlin
DestinyOutlineButton(
    text = "Cancelar",
    onClick = { /* acción */ }
)
```

### DestinyCircularButton
Botón flotante o circular para iconos.
`DestinyCircularButton(icon: ImageVector, modifier: Modifier = Modifier, enabled: Boolean = true, onClick: () -> Unit)`

**Ejemplo de uso:**
```kotlin
DestinyCircularButton(
    icon = Icons.Default.Add,
    onClick = { /* acción */ }
)
```

### DestinyTextButton
Botón de solo texto, discreto.
`DestinyTextButton(text: String, modifier: Modifier = Modifier, onClick: () -> Unit)`

**Ejemplo de uso:**
```kotlin
DestinyTextButton(
    text = "Olvide mi contraseña",
    onClick = { /* acción */ }
)
```

---

## 📝 Formularios (Forms.kt)

### DestinyTextInput
Campo de entrada de texto estilizado con soporte para errores e iconos.
`DestinyTextInput(value: String, onValueChange: (String) -> Unit, label: String, modifier: Modifier = Modifier, placeholder: String? = null, leadingIcon: ImageVector? = null, isError: Boolean = false, errorMessage: String? = null, keyboardType: KeyboardType = KeyboardType.Text)`

**Ejemplo de uso:**
```kotlin
DestinyTextInput(
    value = email,
    onValueChange = { email = it },
    label = "Correo",
    leadingIcon = Icons.Default.Email
)
```

### DestinyPasswordInput
Campo especializado para contraseñas con botón de visibilidad.
`DestinyPasswordInput(value: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier, label: String = "Contraseña")`

**Ejemplo de uso:**
```kotlin
DestinyPasswordInput(
    value = password,
    onValueChange = { password = it }
)
```

---

## 🎴 Tarjetas (Cards.kt)

### DestinyHeroCard
Tarjeta visualmente impactante para eventos o promociones destacadas.
`DestinyHeroCard(title: String, description: String, modifier: Modifier = Modifier, tag: String = "Trending", onActionClick: () -> Unit = {})`

**Ejemplo de uso:**
```kotlin
DestinyHeroCard(
    title = "Festival de Luces",
    description = "Un evento mágico este fin de semana",
    onActionClick = { /* ir al evento */ }
)
```

### DestinyPlaceCard
Tarjeta compacta para mostrar lugares con calificación y distancia.
`DestinyPlaceCard(name: String, category: String, rating: String, distance: String, modifier: Modifier = Modifier)`

**Ejemplo de uso:**
```kotlin
DestinyPlaceCard(
    name = "Central Coffee",
    category = "Cafetería",
    rating = "4.5",
    distance = "A 500m"
)
```

---

## ⚙️ Controles de Selección

### DestinySwitchRow
Fila con interruptor (switch) y etiqueta, ideal para configuraciones.
`DestinySwitchRow(label: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit, modifier: Modifier = Modifier)`

**Ejemplo de uso:**
```kotlin
DestinySwitchRow(
    label = "Modo Oscuro",
    checked = isDark,
    onCheckedChange = { isDark = it }
)
```

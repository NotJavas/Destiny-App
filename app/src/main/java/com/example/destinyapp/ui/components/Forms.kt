package com.example.destinyapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.CloudUpload
import androidx.compose.material.icons.outlined.Error
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.destinyapp.ui.resources.*
import com.example.destinyapp.ui.theme.DestinyAppTheme

/**
 * Input de Texto Estilizado
 */
@Composable
fun DestinyTextInput(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    placeholder: String? = null,
    leadingIcon: ImageVector? = null,
    isError: Boolean = false,
    errorMessage: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            placeholder = placeholder?.let { { Text(it) } },
            leadingIcon = leadingIcon?.let { { Icon(it, contentDescription = null) } },
            isError = isError,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            textStyle = MaterialTheme.typography.bodyLarge,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = DestinyPurple,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                focusedLabelColor = DestinyPurple,
                unfocusedLabelColor = DestinyNeutral600,
                cursorColor = DestinyPurple,
                errorBorderColor = DestinyRed,
                errorLabelColor = DestinyRed
            ),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            singleLine = true
        )
        if (isError && errorMessage != null) {
            Row(
                modifier = Modifier.padding(top = 4.dp, start = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Error,
                    contentDescription = null,
                    tint = DestinyRed,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = errorMessage, 
                    color = DestinyRed, 
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}

/**
 * Input de Contraseña
 */
@Composable
fun DestinyPasswordInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Contraseña"
) {
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
        trailingIcon = {
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(
                    imageVector = if (passwordVisible) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff,
                    contentDescription = null,
                    tint = DestinyNeutral600
                )
            }
        },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        textStyle = MaterialTheme.typography.bodyLarge,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = DestinyPurple,
            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            focusedLabelColor = DestinyPurple,
            unfocusedLabelColor = DestinyNeutral600
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true
    )
}

/**
 * Switch con Contenedor
 */
@Composable
fun DestinySwitchRow(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(12.dp))
            .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(12.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label, 
            color = MaterialTheme.colorScheme.onSurface, 
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium
        )
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = DestinyPurple,
                uncheckedThumbColor = DestinyNeutral600,
                uncheckedTrackColor = DestinyNeutral700
            )
        )
    }
}

/**
 * Checkbox
 */
@Composable
fun DestinyCheckboxRow(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onCheckedChange(!checked) }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = null,
            colors = CheckboxDefaults.colors(
                checkedColor = DestinyPurple,
                uncheckedColor = DestinyNeutral600,
                checkmarkColor = Color.White
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = label, 
            color = MaterialTheme.colorScheme.onSurfaceVariant, 
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

/**
 * Área de Carga
 */
@Composable
fun DestinyFileUploadArea(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(140.dp)
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(12.dp))
            .border(
                BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                RoundedCornerShape(12.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Outlined.CloudUpload,
                contentDescription = null,
                tint = DestinyPurple,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Subir archivos", 
                color = MaterialTheme.colorScheme.onSurface, 
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "Soporta JPG, PNG (Max 5MB)", 
                color = DestinyNeutral600, 
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF181818)
@Composable
fun FormsPreview() {
    DestinyAppTheme {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var isChecked by remember { mutableStateOf(false) }
        var isSwitched by remember { mutableStateOf(true) }

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text("Inputs", color = Color.White, style = MaterialTheme.typography.headlineSmall)
            
            DestinyTextInput(
                value = email,
                onValueChange = { email = it },
                label = "Correo Electrónico",
                placeholder = "ejemplo@destiny.com",
                leadingIcon = Icons.Default.Email,
                keyboardType = KeyboardType.Email
            )

            DestinyPasswordInput(
                value = password,
                onValueChange = { password = it }
            )

            DestinySwitchRow(
                label = "Notificaciones en tiempo real",
                checked = isSwitched,
                onCheckedChange = { isSwitched = it }
            )

            DestinyCheckboxRow(
                label = "Acepto los términos de servicio",
                checked = isChecked,
                onCheckedChange = { isChecked = it }
            )

            DestinyFileUploadArea(onClick = { })
        }
    }
}

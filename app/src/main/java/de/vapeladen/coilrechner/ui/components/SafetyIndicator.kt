package de.vapeladen.coilrechner.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import de.vapeladen.coilrechner.R
import de.vapeladen.coilrechner.data.model.SafetyLevel

@Composable
fun SafetyIndicator(
    level: SafetyLevel,
    message: String = "", // Optional, wird ignoriert wenn leer
    modifier: Modifier = Modifier
) {
    val (backgroundColor, textColor, icon, title, localizedMessage) = when (level) {
        SafetyLevel.SAFE -> {
            Quintuple(
                Color(0xFF4CAF50),
                Color.White,
                Icons.Default.Check,
                stringResource(R.string.safety_safe),
                stringResource(R.string.safety_message_safe_full)
            )
        }
        SafetyLevel.WARNING -> {
            Quintuple(
                Color(0xFFFF9800),
                Color.White,
                Icons.Default.Warning,
                stringResource(R.string.safety_warning),
                stringResource(R.string.safety_message_warning_full)
            )
        }
        SafetyLevel.DANGER -> {
            Quintuple(
                Color(0xFFF44336),
                Color.White,
                Icons.Default.Error,
                stringResource(R.string.safety_danger),
                stringResource(R.string.safety_message_danger_full)
            )
        }
        SafetyLevel.UNKNOWN -> {
            Quintuple(
                Color(0xFF9E9E9E),
                Color.White,
                Icons.Default.Warning,
                "",
                ""
            )
        }
    }
    
    if (level != SafetyLevel.UNKNOWN) {
        Card(
            modifier = modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = backgroundColor
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = textColor,
                    modifier = Modifier.size(24.dp)
                )
                
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = title,
                        color = textColor,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    if (localizedMessage.isNotEmpty()) {
                        Text(
                            text = localizedMessage,
                            color = textColor,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}

data class Quintuple<A, B, C, D, E>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D,
    val fifth: E
)
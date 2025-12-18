package de.vapeladen.coilrechner.ui.tools

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import de.vapeladen.coilrechner.R
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BatteryLifeScreen(onNavigateBack: () -> Unit) {
    var capacityMah by remember { mutableStateOf("") }
    var capacityWh by remember { mutableStateOf("") }
    var outputPower by remember { mutableStateOf("") }
    var avgPuffTime by remember { mutableStateOf("") }
    var totalRuntime by remember { mutableStateOf(0) }
    var numberOfPuffs by remember { mutableStateOf(0) }
    
    fun calculate() {
        val mah = capacityMah.toDoubleOrNull()
        val wh = capacityWh.toDoubleOrNull()
        val power = outputPower.toDoubleOrNull()
        val puffSec = avgPuffTime.toDoubleOrNull()
        
        if (power != null && power > 0) {
            // Calculate from mAh or Wh
            val energyWh = when {
                wh != null && wh > 0 -> wh
                mah != null && mah > 0 -> (mah * 3.7) / 1000.0 // Assuming 3.7V nominal
                else -> null
            }
            
            if (energyWh != null) {
                // Total runtime in hours
                val runtimeHours = energyWh / power
                totalRuntime = (runtimeHours * 3600).roundToInt() // Convert to seconds
                
                // Number of puffs
                if (puffSec != null && puffSec > 0) {
                    numberOfPuffs = (totalRuntime / puffSec).roundToInt()
                }
            }
        }
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.battery_life_title)) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, stringResource(R.string.button_back))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6639B7),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(R.string.battery_capacity),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = capacityMah,
                    onValueChange = { capacityMah = it },
                    label = { Text("mAh") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier.weight(1f)
                )
                
                Text(
                    text = "=",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 4.dp),
                    style = MaterialTheme.typography.headlineMedium
                )
                
                OutlinedTextField(
                    value = capacityWh,
                    onValueChange = { capacityWh = it },
                    label = { Text("Wh") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier.weight(1f)
                )
            }
            
            Divider()
            
            Text(
                text = stringResource(R.string.output_power),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium
            )
            
            OutlinedTextField(
                value = outputPower,
                onValueChange = { outputPower = it },
                label = { Text("W") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth()
            )
            
            Button(
                onClick = { calculate() },
                modifier = Modifier.align(Alignment.End),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2196F3)
                )
            ) {
                Text(stringResource(R.string.button_calculate))
            }
            
            Divider()
            
            Text(
                text = stringResource(R.string.avg_puff_time),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium
            )
            
            OutlinedTextField(
                value = avgPuffTime,
                onValueChange = { avgPuffTime = it },
                label = { Text("sec") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth()
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Button(
                onClick = { calculate() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2196F3)
                )
            ) {
                Text(stringResource(R.string.show_results), modifier = Modifier.padding(vertical = 8.dp))
            }
            
            Button(
                onClick = {
                    capacityMah = ""
                    capacityWh = ""
                    outputPower = ""
                    avgPuffTime = ""
                    totalRuntime = 0
                    numberOfPuffs = 0
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray
                )
            ) {
                Text(stringResource(R.string.clear), modifier = Modifier.padding(vertical = 8.dp))
            }
            
            if (totalRuntime > 0 || numberOfPuffs > 0) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(stringResource(R.string.estimated_runtime))
                            Text(
                                "$totalRuntime",
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.headlineMedium
                            )
                        }
                        
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(stringResource(R.string.estimated_puffs))
                            Text(
                                "$numberOfPuffs",
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.headlineMedium
                            )
                        }
                    }
                }
            }
        }
    }
}
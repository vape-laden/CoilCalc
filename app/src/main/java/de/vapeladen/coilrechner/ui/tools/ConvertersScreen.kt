package de.vapeladen.coilrechner.ui.tools

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
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
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConvertersScreen(onNavigateBack: () -> Unit) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf(
        stringResource(R.string.tab_inch),
        stringResource(R.string.tab_temperature)
    )
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.converters_title)) },
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
        ) {
            TabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = Color(0xFF6639B7),
                contentColor = Color.White
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = {
                            Text(
                                text = title,
                                fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Normal
                            )
                        }
                    )
                }
            }
            
            when (selectedTabIndex) {
                0 -> InchConverter()
                1 -> TemperatureConverter()
            }
        }
    }
}

@Composable
fun InchConverter() {
    var inchInput by remember { mutableStateOf("") }
    var inchFraction by remember { mutableStateOf("") }
    var mmResult by remember { mutableStateOf("") }
    
    fun calculate() {
        val inch = inchInput.toDoubleOrNull()
        if (inch != null) {
            val mm = inch * 25.4
            mmResult = String.format(Locale.US, "%.3f", mm)
        } else if (inchFraction.isNotEmpty()) {
            // Parse fraction like "1/8"
            val parts = inchFraction.split("/")
            if (parts.size == 2) {
                val numerator = parts[0].trim().toDoubleOrNull()
                val denominator = parts[1].trim().toDoubleOrNull()
                if (numerator != null && denominator != null && denominator != 0.0) {
                    val decimal = numerator / denominator
                    val mm = decimal * 25.4
                    mmResult = String.format(Locale.US, "%.3f", mm)
                    inchInput = String.format(Locale.US, "%.4f", decimal)
                }
            }
        }
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = stringResource(R.string.convert_inch_to_mm),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.fillMaxWidth()
        )
        
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(0.9f),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = inchFraction,
                    onValueChange = { 
                        inchFraction = it
                        calculate()
                    },
                    label = { Text(stringResource(R.string.fraction_hint)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    modifier = Modifier.weight(1f)
                )
            }
            
            Text(stringResource(R.string.or), style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
            
            OutlinedTextField(
                value = inchInput,
                onValueChange = { 
                    inchInput = it
                    inchFraction = ""
                    calculate()
                },
                label = { Text("Inch") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth(0.7f)
            )
            
            Text("=", style = MaterialTheme.typography.headlineLarge)
            
            OutlinedTextField(
                value = mmResult,
                onValueChange = {},
                label = { Text("mm") },
                readOnly = true,
                modifier = Modifier.fillMaxWidth(0.7f)
            )
        }
    }
}

@Composable
fun TemperatureConverter() {
    var celsiusInput by remember { mutableStateOf("") }
    var fahrenheitResult by remember { mutableStateOf("") }
    
    fun calculate() {
        val celsius = celsiusInput.toDoubleOrNull()
        if (celsius != null) {
            val fahrenheit = (celsius * 9.0 / 5.0) + 32
            fahrenheitResult = String.format(Locale.US, "%.1f", fahrenheit)
        }
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = stringResource(R.string.convert_celsius_fahrenheit),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.fillMaxWidth()
        )
        
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = celsiusInput,
                onValueChange = { 
                    celsiusInput = it
                    calculate()
                },
                label = { Text("°C") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth(0.7f)
            )
            
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                modifier = Modifier.size(48.dp),
                tint = Color.Gray
            )
            
            OutlinedTextField(
                value = fahrenheitResult,
                onValueChange = {},
                label = { Text("°F") },
                readOnly = true,
                modifier = Modifier.fillMaxWidth(0.7f)
            )
        }
    }
}
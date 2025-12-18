package de.vapeladen.coilrechner.ui.calculator

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.vapeladen.coilrechner.R
import de.vapeladen.coilrechner.data.model.*
import de.vapeladen.coilrechner.ui.components.ResultCard
import de.vapeladen.coilrechner.ui.components.SafetyIndicator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorScreen(
    viewModel: CalculatorViewModel = viewModel(),
    onNavigateToDeveloperInfo: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        // Calculation Mode Selector
        CalculationModeSelector(
            selectedMode = uiState.calculationMode,
            onModeSelected = { viewModel.setCalculationMode(it) }
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Wire Material Selector
        MaterialSelector(
            selectedMaterial = uiState.selectedMaterial,
            onMaterialSelected = { viewModel.selectMaterial(it) }
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Wire Gauge/Diameter
        WireGaugeSelector(
            selectedAwg = uiState.selectedAwg,
            wireDiameterMm = uiState.wireDiameterMm,
            onAwgSelected = { viewModel.selectAwg(it) },
            onDiameterChanged = { viewModel.updateWireDiameter(it) }
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Coil ID
        OutlinedTextField(
            value = uiState.coilIdMm,
            onValueChange = { viewModel.updateCoilId(it) },
            label = { Text(stringResource(R.string.label_coil_id)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Wraps or Target Resistance based on mode
        when (uiState.calculationMode) {
            CalculationMode.FROM_WRAPS -> {
                OutlinedTextField(
                    value = uiState.wraps,
                    onValueChange = { viewModel.updateWraps(it) },
                    label = { Text(stringResource(R.string.label_wraps)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier.fillMaxWidth()
                )
            }
            CalculationMode.FROM_RESISTANCE -> {
                OutlinedTextField(
                    value = uiState.targetResistance,
                    onValueChange = { viewModel.updateTargetResistance(it) },
                    label = { Text(stringResource(R.string.label_target_resistance)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Coil Type Selector
        CoilTypeSelector(
            selectedType = uiState.selectedCoilType,
            onTypeSelected = { viewModel.selectCoilType(it) }
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Vaping Style Selector
        VapingStyleSelector(
            selectedStyle = uiState.selectedStyle,
            onStyleSelected = { viewModel.selectVapingStyle(it) }
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Power Input
        OutlinedTextField(
            value = uiState.power,
            onValueChange = { viewModel.updatePower(it) },
            label = { Text(stringResource(R.string.label_power)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Battery CDR
        OutlinedTextField(
            value = uiState.batteryCDR,
            onValueChange = { viewModel.updateBatteryCDR(it) },
            label = { Text(stringResource(R.string.label_battery_cdr)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Calculate Button
        Button(
            onClick = { viewModel.calculate() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6639B7)
            )
        ) {
            Text(
                text = stringResource(R.string.button_calculate),
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Error Message
        uiState.errorMessage?.let { error ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer
                )
            ) {
                Text(
                    text = error,
                    color = MaterialTheme.colorScheme.onErrorContainer,
                    modifier = Modifier.padding(16.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
        
        // Results
        uiState.result?.let { result ->
            if (uiState.showResults) {
                ResultsSection(result = result)
            }
        }
        
        Spacer(modifier = Modifier.height(80.dp)) // Space for FAB
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculationModeSelector(
    selectedMode: CalculationMode,
    onModeSelected: (CalculationMode) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FilterChip(
            selected = selectedMode == CalculationMode.FROM_WRAPS,
            onClick = { onModeSelected(CalculationMode.FROM_WRAPS) },
            label = { Text(stringResource(R.string.mode_wraps_to_resistance)) },
            modifier = Modifier.weight(1f)
        )
        FilterChip(
            selected = selectedMode == CalculationMode.FROM_RESISTANCE,
            onClick = { onModeSelected(CalculationMode.FROM_RESISTANCE) },
            label = { Text(stringResource(R.string.mode_resistance_to_wraps)) },
            modifier = Modifier.weight(1f)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaterialSelector(
    selectedMaterial: WireMaterial,
    onMaterialSelected: (WireMaterial) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val materials = WireMaterial.getAllMaterials()
    
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it }
    ) {
        OutlinedTextField(
            value = selectedMaterial.name,
            onValueChange = {},
            readOnly = true,
            label = { Text(stringResource(R.string.label_wire_material)) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor()
        )
        
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            materials.forEach { material ->
                DropdownMenuItem(
                    text = { Text(material.name) },
                    onClick = {
                        onMaterialSelected(material)
                        expanded = false
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WireGaugeSelector(
    selectedAwg: Int,
    wireDiameterMm: String,
    onAwgSelected: (Int) -> Unit,
    onDiameterChanged: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val gauges = WireGauge.getAllGauges()
    
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = it },
            modifier = Modifier.weight(1f)
        ) {
            OutlinedTextField(
                value = "$selectedAwg AWG",
                onValueChange = {},
                readOnly = true,
                label = { Text("AWG") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )
            
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                gauges.forEach { gauge ->
                    DropdownMenuItem(
                        text = { Text("${gauge.awg} AWG (${gauge.diameterMm} mm)") },
                        onClick = {
                            onAwgSelected(gauge.awg)
                            expanded = false
                        }
                    )
                }
            }
        }
        
        OutlinedTextField(
            value = wireDiameterMm,
            onValueChange = onDiameterChanged,
            label = { Text("Ø mm") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.weight(1f)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoilTypeSelector(
    selectedType: CoilType,
    onTypeSelected: (CoilType) -> Unit
) {
    Column {
        Text(
            text = stringResource(R.string.label_coil_type),
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CoilType.values().take(3).forEach { type ->
                FilterChip(
                    selected = selectedType == type,
                    onClick = { onTypeSelected(type) },
                    label = { Text(type.name) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VapingStyleSelector(
    selectedStyle: VapingStyle,
    onStyleSelected: (VapingStyle) -> Unit
) {
    Column {
        Text(
            text = stringResource(R.string.label_vaping_style),
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            VapingStyle.values().forEach { style ->
                FilterChip(
                    selected = selectedStyle == style,
                    onClick = { onStyleSelected(style) },
                    label = { Text(style.name) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun ResultsSection(result: CoilResult) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = stringResource(R.string.results_title),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            
            // Safety Indicator
            SafetyIndicator(
                level = result.safetyLevel,
                message = result.safetyMessage
            )
            
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            
            // Results
            result.wrapsNeeded?.let {
                ResultRow(
                    label = stringResource(R.string.result_wraps_needed),
                    value = String.format("%.2f", it)
                )
            }
            
            ResultRow(
                label = stringResource(R.string.result_resistance),
                value = "${String.format("%.3f", result.resistance)} Ω"
            )
            
            ResultRow(
                label = stringResource(R.string.result_wire_length),
                value = "${String.format("%.2f", result.wireLength)} mm"
            )
            
            result.current?.let {
                ResultRow(
                    label = stringResource(R.string.result_current),
                    value = "${String.format("%.2f", it)} A"
                )
            }
            
            result.power?.let {
                ResultRow(
                    label = stringResource(R.string.label_power),
                    value = "${String.format("%.1f", it)} W"
                )
            }
            
            result.style?.let {
                ResultRow(
                    label = stringResource(R.string.label_vaping_style),
                    value = it.name + " (${it.getRecommendedPowerRange()})"
                )
            }
            
            ResultRow(
                label = stringResource(R.string.result_surface_area),
                value = "${String.format("%.2f", result.surfaceArea)} mm²"
            )
        }
    }
}

@Composable
fun ResultRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
    }
}
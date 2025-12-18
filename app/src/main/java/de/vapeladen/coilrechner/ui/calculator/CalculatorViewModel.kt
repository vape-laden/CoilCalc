package de.vapeladen.coilrechner.ui.calculator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.vapeladen.coilrechner.data.model.*
import de.vapeladen.coilrechner.domain.CoilCalculator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CalculatorUiState(
    val selectedMaterial: WireMaterial = WireMaterial.KANTHAL_A1,
    val wireDiameterMm: String = "0.405",
    val selectedAwg: Int = 26,
    val coilIdMm: String = "3.0",
    val wraps: String = "6",
    val targetResistance: String = "",
    val selectedCoilType: CoilType = CoilType.SINGLE,
    val selectedStyle: VapingStyle = VapingStyle.MTL,
    val power: String = "15",
    val voltage: String = "",
    val batteryCDR: String = "20",
    val calculationMode: CalculationMode = CalculationMode.FROM_WRAPS,
    val result: CoilResult? = null,
    val showResults: Boolean = false,
    val errorMessage: String? = null
)

enum class CalculationMode {
    FROM_WRAPS,      // Calculate resistance from wraps
    FROM_RESISTANCE  // Calculate wraps from target resistance
}

class CalculatorViewModel : ViewModel() {
    
    private val _uiState = MutableStateFlow(CalculatorUiState())
    val uiState: StateFlow<CalculatorUiState> = _uiState.asStateFlow()
    
    fun selectMaterial(material: WireMaterial) {
        _uiState.update { it.copy(selectedMaterial = material) }
    }
    
    fun selectAwg(awg: Int) {
        WireGauge.getDiameter(awg)?.let { diameter ->
            _uiState.update { 
                it.copy(
                    selectedAwg = awg,
                    wireDiameterMm = diameter.toString()
                )
            }
        }
    }
    
    fun updateWireDiameter(value: String) {
        _uiState.update { it.copy(wireDiameterMm = value) }
    }
    
    fun updateCoilId(value: String) {
        _uiState.update { it.copy(coilIdMm = value) }
    }
    
    fun updateWraps(value: String) {
        _uiState.update { it.copy(wraps = value) }
    }
    
    fun updateTargetResistance(value: String) {
        _uiState.update { it.copy(targetResistance = value) }
    }
    
    fun selectCoilType(type: CoilType) {
        _uiState.update { it.copy(selectedCoilType = type) }
    }
    
    fun selectVapingStyle(style: VapingStyle) {
        _uiState.update { it.copy(selectedStyle = style) }
    }
    
    fun updatePower(value: String) {
        _uiState.update { it.copy(power = value) }
    }
    
    fun updateVoltage(value: String) {
        _uiState.update { it.copy(voltage = value) }
    }
    
    fun updateBatteryCDR(value: String) {
        _uiState.update { it.copy(batteryCDR = value) }
    }
    
    fun setCalculationMode(mode: CalculationMode) {
        _uiState.update { it.copy(calculationMode = mode) }
    }
    
    fun calculate() {
        viewModelScope.launch {
            try {
                val state = _uiState.value
                
                // Parse inputs
                val wireDiameter = state.wireDiameterMm.toDoubleOrNull()
                val coilId = state.coilIdMm.toDoubleOrNull()
                val power = state.power.toDoubleOrNull()
                val batteryCDR = state.batteryCDR.toDoubleOrNull()
                
                if (wireDiameter == null || wireDiameter <= 0) {
                    _uiState.update {
                        it.copy(
                            errorMessage = "Invalid wire diameter",
                            showResults = false
                        )
                    }
                    return@launch
                }
                
                if (coilId == null || coilId <= 0) {
                    _uiState.update {
                        it.copy(
                            errorMessage = "Invalid inner diameter",
                            showResults = false
                        )
                    }
                    return@launch
                }
                
                val result = when (state.calculationMode) {
                    CalculationMode.FROM_WRAPS -> {
                        val wraps = state.wraps.toDoubleOrNull()
                        if (wraps == null || wraps <= 0) {
                            _uiState.update {
                                it.copy(
                                    errorMessage = "Invalid number of wraps",
                                    showResults = false
                                )
                            }
                            return@launch
                        }
                        
                        val specs = CoilSpecs(
                            material = state.selectedMaterial,
                            wireDiameterMm = wireDiameter,
                            coilIdMm = coilId,
                            wraps = wraps,
                            coilType = state.selectedCoilType
                        )
                        
                        CoilCalculator.calculateComplete(
                            specs = specs,
                            power = power,
                            batteryCDR = batteryCDR
                        )
                    }
                    
                    CalculationMode.FROM_RESISTANCE -> {
                        val targetRes = state.targetResistance.toDoubleOrNull()
                        if (targetRes == null || targetRes <= 0) {
                            _uiState.update {
                                it.copy(
                                    errorMessage = "Invalid target resistance",
                                    showResults = false
                                )
                            }
                            return@launch
                        }
                        
                        val calculatedWraps = CoilCalculator.calculateWrapsForResistance(
                            material = state.selectedMaterial,
                            wireDiameterMm = wireDiameter,
                            coilIdMm = coilId,
                            targetResistance = targetRes,
                            coilType = state.selectedCoilType
                        )
                        
                        val specs = CoilSpecs(
                            material = state.selectedMaterial,
                            wireDiameterMm = wireDiameter,
                            coilIdMm = coilId,
                            wraps = calculatedWraps,
                            coilType = state.selectedCoilType
                        )
                        
                        val fullResult = CoilCalculator.calculateComplete(
                            specs = specs,
                            power = power,
                            batteryCDR = batteryCDR
                        )
                        
                        fullResult.copy(wrapsNeeded = calculatedWraps)
                    }
                }
                
                _uiState.update { 
                    it.copy(
                        result = result,
                        showResults = true,
                        errorMessage = null
                    )
                }
                
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        errorMessage = "Calculation error: ${e.message}",
                        showResults = false
                    )
                }
            }
        }
    }
    
    fun reset() {
        _uiState.value = CalculatorUiState()
    }
}
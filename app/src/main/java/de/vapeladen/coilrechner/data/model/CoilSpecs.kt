package de.vapeladen.coilrechner.data.model

/**
 * Coil specification and geometry
 */
data class CoilSpecs(
    val material: WireMaterial,
    val wireDiameterMm: Double,
    val coilIdMm: Double,
    val wraps: Double,
    val coilType: CoilType,
    val legLengthMm: Double = 5.0
)

/**
 * Coil type configuration
 */
enum class CoilType(val displayName: String, val multiplier: Double) {
    SINGLE("Single Coil", 1.0),
    DUAL("Dual Coil", 0.5),
    PARALLEL("Parallel", 0.5),
    TWISTED("Twisted", 0.5),
    CLAPTON("Clapton", 0.8) // Simplified for basic Clapton
}

/**
 * Vaping style with typical resistance and power ranges
 */
enum class VapingStyle(
    val displayName: String,
    val resistanceMin: Double,
    val resistanceMax: Double,
    val powerMin: Double,
    val powerMax: Double
) {
    MTL(
        displayName = "MTL (Backendampfen)",
        resistanceMin = 0.6,
        resistanceMax = 2.8,
        powerMin = 8.0,
        powerMax = 18.0
    ),
    RDL(
        displayName = "RDL (Restricted DL)",
        resistanceMin = 0.5,
        resistanceMax = 0.9,
        powerMin = 15.0,
        powerMax = 35.0
    ),
    DL(
        displayName = "DL (Direkte Lunge)",
        resistanceMin = 0.1,
        resistanceMax = 0.6,
        powerMin = 40.0,
        powerMax = 120.0
    );
    
    fun isInRange(resistance: Double): Boolean {
        return resistance in resistanceMin..resistanceMax
    }
    
    fun getRecommendedPowerRange(): String {
        return "${powerMin.toInt()}-${powerMax.toInt()}W"
    }
}

/**
 * Coil calculation result
 */
data class CoilResult(
    val resistance: Double,
    val wrapsNeeded: Double? = null,
    val wireLength: Double,
    val surfaceArea: Double,
    val current: Double? = null,
    val voltage: Double? = null,
    val power: Double? = null,
    val heatFlux: Double? = null,
    val style: VapingStyle? = null,
    val safetyLevel: SafetyLevel = SafetyLevel.UNKNOWN,
    val safetyMessage: String = ""
)

/**
 * Safety assessment levels
 */
enum class SafetyLevel(val colorHex: String) {
    SAFE("#4CAF50"),      // Green
    WARNING("#FF9800"),    // Orange
    DANGER("#F44336"),     // Red
    UNKNOWN("#9E9E9E")     // Gray
}

/**
 * Battery safety check result
 */
data class BatteryCheck(
    val batteryCDR: Double,
    val calculatedCurrent: Double,
    val safetyMargin: Double,
    val level: SafetyLevel,
    val message: String
) {
    companion object {
        private const val SAFETY_MARGIN_PERCENT = 0.20 // 20% safety margin
        
        fun check(batteryCDR: Double, current: Double): BatteryCheck {
            val safeCurrentLimit = batteryCDR * (1 - SAFETY_MARGIN_PERCENT)
            val safetyMargin = (batteryCDR - current) / batteryCDR
            
            val level = when {
                current > batteryCDR -> SafetyLevel.DANGER
                current > safeCurrentLimit -> SafetyLevel.WARNING
                else -> SafetyLevel.SAFE
            }
            
            // Message wird im ViewModel gesetzt basierend auf level
            return BatteryCheck(
                batteryCDR = batteryCDR,
                calculatedCurrent = current,
                safetyMargin = safetyMargin,
                level = level,
                message = "" // Will be set in ViewModel from string resources
            )
        }
    }
}
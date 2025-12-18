package de.vapeladen.coilrechner.data.model

/**
 * Wire material data with specific resistance values
 * Based on Steam Engine and industry standards
 */
data class WireMaterial(
    val id: String,
    val name: String,
    val resistivity: Double, // Ω·mm²/m at 20°C
    val tempCoefficient: Double = 0.0, // Temperature coefficient for TC
    val tcCapable: Boolean = false,
    val wattOnly: Boolean = false,
    val description: String = ""
) {
    companion object {
        // Resistivity values in Ω·mm²/m at 20°C
        val KANTHAL_A1 = WireMaterial(
            id = "kanthal_a1",
            name = "Kanthal A1",
            resistivity = 1.45,
            tempCoefficient = 0.0,
            tcCapable = false,
            wattOnly = true,
            description = "FeCrAl alloy, high heat resistance, stable resistance, ideal for watt mode"
        )
        
        val NICHROME_80 = WireMaterial(
            id = "ni80",
            name = "Nichrome 80",
            resistivity = 1.09,
            tempCoefficient = 0.0,
            tcCapable = false,
            wattOnly = true,
            description = "NiCr alloy, fast ramp-up, lower resistance, popular for performance builds"
        )
        
        val SS316L = WireMaterial(
            id = "ss316l",
            name = "Stainless Steel 316L",
            resistivity = 0.75,
            tempCoefficient = 0.00088,
            tcCapable = true,
            wattOnly = false,
            description = "Versatile wire, works in watt and TC mode, clean flavor"
        )
        
        val NICKEL_200 = WireMaterial(
            id = "ni200",
            name = "Nickel 200",
            resistivity = 0.096,
            tempCoefficient = 0.006,
            tcCapable = true,
            wattOnly = false,
            description = "Pure nickel, TC only, high temperature coefficient"
        )
        
        val TITANIUM_G1 = WireMaterial(
            id = "ti_g1",
            name = "Titanium Grade 1",
            resistivity = 0.56,
            tempCoefficient = 0.0035,
            tcCapable = true,
            wattOnly = false,
            description = "Titanium wire, TC capable, do not dry burn"
        )
        
        fun getAllMaterials(): List<WireMaterial> = listOf(
            KANTHAL_A1,
            NICHROME_80,
            SS316L,
            NICKEL_200,
            TITANIUM_G1
        )
        
        fun getById(id: String): WireMaterial? = getAllMaterials().find { it.id == id }
    }
}

/**
 * Wire gauge with AWG to diameter conversion
 */
data class WireGauge(
    val awg: Int,
    val diameterMm: Double
) {
    companion object {
        // Standard AWG to mm conversion table
        private val awgTable = mapOf(
            20 to 0.812,
            22 to 0.644,
            24 to 0.511,
            26 to 0.405,
            28 to 0.321,
            30 to 0.255,
            32 to 0.202,
            34 to 0.160,
            36 to 0.127,
            38 to 0.101,
            40 to 0.0799
        )
        
        fun getDiameter(awg: Int): Double? = awgTable[awg]
        
        fun getAllGauges(): List<WireGauge> = awgTable.map { WireGauge(it.key, it.value) }
        
        fun fromDiameter(diameterMm: Double): WireGauge? {
            return awgTable.entries
                .minByOrNull { kotlin.math.abs(it.value - diameterMm) }
                ?.let { WireGauge(it.key, it.value) }
        }
    }
}
package de.vapeladen.coilrechner.domain

import de.vapeladen.coilrechner.data.model.*
import kotlin.math.PI
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Core coil calculation engine
 * Based on Ohm's Law and wire resistance physics
 */
object CoilCalculator {
    
    /**
     * Calculate coil resistance from specifications
     */
    fun calculateResistance(specs: CoilSpecs): Double {
        val wireLength = calculateWireLength(
            coilIdMm = specs.coilIdMm,
            wireDiameterMm = specs.wireDiameterMm,
            wraps = specs.wraps,
            legLengthMm = specs.legLengthMm
        )
        
        val crossSectionalArea = calculateCrossSectionalArea(specs.wireDiameterMm)
        
        // R = ρ × L / A
        // ρ in Ω·mm²/m, L in m, A in mm²
        val wireLengthM = wireLength / 1000.0
        val resistancePerCoil = specs.material.resistivity * wireLengthM / crossSectionalArea
        
        // Apply coil type multiplier (e.g., dual coil halves the resistance)
        return resistancePerCoil * specs.coilType.multiplier
    }
    
    /**
     * Calculate number of wraps needed for target resistance
     */
    fun calculateWrapsForResistance(
        material: WireMaterial,
        wireDiameterMm: Double,
        coilIdMm: Double,
        targetResistance: Double,
        coilType: CoilType,
        legLengthMm: Double = 5.0
    ): Double {
        // Adjust target resistance for coil type
        val adjustedTarget = targetResistance / coilType.multiplier
        
        val crossSectionalArea = calculateCrossSectionalArea(wireDiameterMm)
        val coilCircumference = PI * (coilIdMm + wireDiameterMm)
        val legLengthM = legLengthMm / 1000.0
        
        // R = ρ × L / A
        // L = (wraps × circumference) + legLength
        // R = ρ × ((wraps × circumference) + legLength) / A
        // Solve for wraps:
        // wraps = (R × A / ρ - legLength) / circumference
        
        val resistivityOhmM = material.resistivity
        val circumferenceM = coilCircumference / 1000.0
        
        val wraps = (adjustedTarget * crossSectionalArea / resistivityOhmM - legLengthM) / circumferenceM
        
        return wraps.coerceAtLeast(0.1)
    }
    
    /**
     * Calculate complete coil result with all parameters
     */
    fun calculateComplete(
        specs: CoilSpecs,
        power: Double? = null,
        voltage: Double? = null,
        batteryCDR: Double? = null
    ): CoilResult {
        val resistance = calculateResistance(specs)
        val wireLength = calculateWireLength(
            coilIdMm = specs.coilIdMm,
            wireDiameterMm = specs.wireDiameterMm,
            wraps = specs.wraps,
            legLengthMm = specs.legLengthMm
        )
        val surfaceArea = calculateSurfaceArea(
            coilIdMm = specs.coilIdMm,
            wireDiameterMm = specs.wireDiameterMm,
            wraps = specs.wraps
        )
        
        // Calculate electrical parameters
        val (actualVoltage, actualPower, current) = when {
            power != null -> {
                // Given power, calculate voltage and current
                val v = sqrt(power * resistance)
                val i = power / v
                Triple(v, power, i)
            }
            voltage != null -> {
                // Given voltage, calculate power and current
                val p = voltage.pow(2) / resistance
                val i = voltage / resistance
                Triple(voltage, p, i)
            }
            else -> Triple(null, null, null)
        }
        
        // Calculate heat flux if power is available
        val heatFlux = if (actualPower != null) {
            actualPower / surfaceArea // W/mm²
        } else null
        
        // Determine vaping style based on resistance
        val style = VapingStyle.values().find { it.isInRange(resistance) }
        
        // Safety check
        val (safetyLevel, safetyMessage) = if (current != null && batteryCDR != null) {
            val batteryCheck = BatteryCheck.check(batteryCDR, current)
            batteryCheck.level to batteryCheck.message
        } else {
            SafetyLevel.UNKNOWN to ""
        }
        
        return CoilResult(
            resistance = resistance.roundToDecimals(3),
            wireLength = wireLength.roundToDecimals(2),
            surfaceArea = surfaceArea.roundToDecimals(2),
            current = current?.roundToDecimals(2),
            voltage = actualVoltage?.roundToDecimals(2),
            power = actualPower?.roundToDecimals(1),
            heatFlux = heatFlux?.roundToDecimals(3),
            style = style,
            safetyLevel = safetyLevel,
            safetyMessage = safetyMessage
        )
    }
    
    /**
     * Calculate wire length in mm
     */
    private fun calculateWireLength(
        coilIdMm: Double,
        wireDiameterMm: Double,
        wraps: Double,
        legLengthMm: Double
    ): Double {
        // Each wrap travels around the coil ID plus the wire diameter
        val coilCircumference = PI * (coilIdMm + wireDiameterMm)
        val coilLength = wraps * coilCircumference
        val totalLength = coilLength + (2 * legLengthMm)
        return totalLength
    }
    
    /**
     * Calculate cross-sectional area of wire in mm²
     */
    private fun calculateCrossSectionalArea(wireDiameterMm: Double): Double {
        val radius = wireDiameterMm / 2.0
        return PI * radius.pow(2)
    }
    
    /**
     * Calculate outer surface area of coil in mm²
     */
    private fun calculateSurfaceArea(
        coilIdMm: Double,
        wireDiameterMm: Double,
        wraps: Double
    ): Double {
        // Surface area of a cylinder: 2πr × h
        // r = (coilID + wireDiameter) / 2
        // h = wraps × wireDiameter
        val coilRadius = (coilIdMm + wireDiameterMm) / 2.0
        val coilHeight = wraps * wireDiameterMm
        return 2 * PI * coilRadius * coilHeight
    }
    
    /**
     * Calculate current from resistance and power/voltage
     * I = V / R  or  I = √(P / R)
     */
    fun calculateCurrent(resistance: Double, power: Double? = null, voltage: Double? = null): Double? {
        return when {
            voltage != null -> voltage / resistance
            power != null -> sqrt(power / resistance)
            else -> null
        }
    }
    
    /**
     * Calculate power from voltage and resistance
     * P = V² / R
     */
    fun calculatePower(voltage: Double, resistance: Double): Double {
        return voltage.pow(2) / resistance
    }
    
    /**
     * Calculate voltage from power and resistance
     * V = √(P × R)
     */
    fun calculateVoltage(power: Double, resistance: Double): Double {
        return sqrt(power * resistance)
    }
    
    /**
     * Helper to round doubles to specified decimal places
     */
    private fun Double.roundToDecimals(decimals: Int): Double {
        val multiplier = 10.0.pow(decimals)
        return kotlin.math.round(this * multiplier) / multiplier
    }
}
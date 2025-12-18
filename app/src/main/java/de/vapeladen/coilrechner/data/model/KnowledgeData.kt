package de.vapeladen.coilrechner.data.model

import java.util.Locale

/**
 * Data models for Knowledge Zone content
 */
data class DictionaryEntry(
    val title: String,
    val content: String
)

data class FAQEntry(
    val question: String,
    val answer: String
)

data class WireEntry(
    val name: String,
    val composition: String,
    val properties: String,
    val pros: List<String>,
    val cons: List<String>
)

object KnowledgeDataEN {
    
    fun getDictionaryEntries(): List<DictionaryEntry> = listOf(
        DictionaryEntry(
            "Rebuildable Dripping Atomizer (RDA)",
            "An RDA is a type of rebuildable atomizer characterized by low liquid capacity, excellent flavor, large vapor production, and ample airflow. Users drip e-liquid directly onto the coils. RDAs allow building multiple coils, offer compact size and affordable pricing. Leakage may occasionally occur."
        ),
        DictionaryEntry(
            "E-Liquid",
            "E-Liquid is a liquid used in e-cigarettes that vaporizes when heated. It typically contains nicotine (0% to 3.6%), propylene glycol (PG), vegetable glycerin (VG), and flavorings. Additional ingredients may include sour agents, water, ethyl maltol, or ethanol."
        ),
        DictionaryEntry(
            "Vegetable Glycerin (VG)",
            "VG is a thick liquid known for producing dense vapor with a mild taste. It moves slowly toward the coil and is recommended for use in RDAs and modern sub-ohm tanks."
        ),
        DictionaryEntry(
            "Propylene Glycol (PG)",
            "PG is smoother than VG, carries flavor better, but produces less vapor. It is a key ingredient in e-liquid formulations to balance vapor production and flavor."
        ),
        DictionaryEntry(
            "Nickel Wire (Ni200)",
            "Ni200 wire offers low and temperature-sensitive resistance, making it suitable exclusively for Temperature Control (TC) vaping modes."
        ),
        DictionaryEntry(
            "Air Hole (AH)",
            "Air Holes allow air to flow through the atomizer, affecting vapor temperature and density. Larger holes cool the vapor but can reduce density."
        ),
        DictionaryEntry(
            "Flavor",
            "Flavoring gives e-liquids their distinct tastes, ranging from fruity to tobacco-like, and enhances the vaping experience."
        ),
        DictionaryEntry(
            "Nicotine Base",
            "Nicotine base is a solution containing nicotine in concentrations up to 3.6% or higher, mixed with VG and PG. Nicotine-free bases also exist."
        ),
        DictionaryEntry(
            "Short Circuit (S/C)",
            "A short circuit occurs when unintended contact creates a low-resistance electrical path, potentially damaging devices or creating a safety hazard."
        ),
        DictionaryEntry(
            "Stainless Steel (SS)",
            "Stainless steel wire is used in both wattage mode and temperature control mode. It is durable, provides good flavor, and is versatile for coil building."
        ),
        DictionaryEntry(
            "Sub-Ohm Vaping",
            "Sub-ohm vaping refers to using coils with resistance below 1.0 ohm, resulting in larger vapor clouds, warmer vapor, and intensified flavor."
        ),
        DictionaryEntry(
            "Temperature Control (TC)",
            "TC mode allows the device to regulate coil temperature by monitoring resistance changes, preventing dry hits and improving consistency."
        ),
        DictionaryEntry(
            "Velocity Deck Style",
            "A velocity deck is a two-post coil-building deck with large post holes, making coil installation easy and efficient for dual-coil builds."
        ),
        DictionaryEntry(
            "Voltage Drop",
            "Voltage drop describes the loss of power between the battery and the atomizer, often occurring in mechanical mods. Lower voltage drop equals better performance."
        ),
        DictionaryEntry(
            "510 Connection",
            "The 510 connection is a standard threading system for attaching atomizers to devices. It supports replaceable drip tips and offers broad compatibility across vaping products."
        ),
        DictionaryEntry(
            "18650 Batteries",
            "18650 batteries are rechargeable lithium-ion cells used in high-power devices. They measure 18.6 mm × 65.2 mm and typically offer 1500–3400 mAh with a nominal voltage of 3.7 V."
        ),
        DictionaryEntry(
            "Ego Style E-Cigarettes",
            "Ego-style e-cigarettes refer to early pen-style vape devices featuring simple operation, built-in batteries, and compatibility with clearomizers."
        ),
        DictionaryEntry(
            "Variable Voltage (VV)",
            "Variable Voltage (VV) devices allow users to manually adjust the output voltage. This gives precise control over vapor intensity, flavor warmth, and throat hit."
        ),
        DictionaryEntry(
            "Variable Wattage (VW)",
            "Variable Wattage (VW) devices automatically adjust voltage to maintain the user-selected power output. This results in more consistent performance regardless of coil resistance changes."
        )
    )
    
    fun getFAQEntries(): List<FAQEntry> = listOf(
        FAQEntry(
            "Setting Maximum Power on Devices",
            "Electronic MODs allow users to adjust voltage while coil resistance remains constant. Power (P) depends on voltage (U) and resistance (R), according to Ohm's Law:\n\nP = U² / R\n\nA device's maximum voltage and wattage limits determine the highest coil resistance it can power effectively.\n\nExample: A 50W device with a 10V limit can power up to a 2-ohm coil (since 10² / 50 = 2). Even if a device offers high wattage (e.g., 150W), it must still respect these electrical limits."
        ),
        FAQEntry(
            "Choosing Between Single and Dual Coils",
            "Dual coils may seem like they should produce twice the vapor of a single coil, but performance depends on heat distribution, power dissipation, coil surface area, and wick saturation.\n\nIncreasing surface area (more coils, more wraps, wider inner diameter) spreads the power across more metal, reducing heat per coil.\n\nExample: A 20W device with dual coils delivers 10W per coil. If the device has low power output, a single coil is more efficient.\n\nToo much heat can burn the cotton, so finding the right balance is essential."
        ),
        FAQEntry(
            "Does Coil Diameter Matter?",
            "Yes. Coil diameter determines resistance, heat capacity, and airflow:\n\n• Measured in AWG (American Wire Gauge) or millimeters (mm)\n• Higher AWG = thinner wire = higher resistance\n• Thicker wires allow more current flow\n• Thicker wires store more heat and take longer to heat/cool\n\nCoil diameter affects ramp-up time, flavor, vapor production, and wick saturation."
        ),
        FAQEntry(
            "Batteries: Series vs. Parallel Configuration",
            "Series Configuration:\n• Voltage adds up (e.g., 3.7V + 3.7V = 7.4V)\n• Amp limit stays the same\n• Allows building higher-resistance coils\n• Total capacity = (C1 × C2) / (C1 + C2)\n\nParallel Configuration:\n• Voltage stays the same (3.7V)\n• Amp limit doubles\n• Battery capacity doubles\n• Example: two 2600mAh, 20A batteries → 5200mAh capacity, 40A limit\n\nBoth can produce the same wattage:\nSeries: 7.4V × 30A = 222W\nParallel: 3.7V × 60A = 222W"
        ),
        FAQEntry(
            "Mechanical vs. Electronic Mods",
            "Mechanical Mods:\n• Pure battery + switch (no electronics)\n• No safety protections\n• Common batteries: 18650, 26650, 18500, 18350\n• Power based on Ohm's Law: P = U² / R\n• Should only be used by advanced users\n\nElectronic Mods:\n• Active electronics + LCD + protections\n• Short-circuit protection, cutoff timer, overheat protection\n• Variable Voltage (VV)\n• Variable Wattage (VW)\n• Temperature Control (TC)\n• Safe for beginners with proper battery handling"
        )
    )
    
    fun getWireEntries(): List<WireEntry> = listOf(
        WireEntry(
            "Kanthal (FeCrAl)",
            "Iron (Fe): 60–75%, Chromium (Cr): 20–30%, Aluminium (Al): 4–7.5%",
            "Used in Variable Wattage (VW) mode. High resistance. Ideal choice for beginners.",
            listOf(
                "Handles high temperatures",
                "Easy to shape and bend",
                "Inexpensive",
                "Stable resistance",
                "Compatible with mechanical mods",
                "Holds its shape well",
                "Widely available"
            ),
            listOf(
                "May lose flavor quality over long-term use",
                "Can produce metallic aftertaste when freshly installed",
                "Slower heating compared to other wires"
            )
        ),
        WireEntry(
            "Nichrome (NiCr)",
            "Nickel (Ni): 80%, Chromium (Cr): 20%",
            "Used in VW mode. Changes color during heating. Low resistance. Stays hot longer.",
            listOf(
                "Heats very quickly",
                "Produces good flavor",
                "Easy to work with",
                "Affordable"
            ),
            listOf(
                "Contains nickel (can cause allergies)",
                "Lower melting point than Kanthal or SS"
            )
        ),
        WireEntry(
            "Stainless Steel (SS316)",
            "Nickel (Ni): 8%, Chromium (Cr): 18%, Molybdenum (Mo): 2%",
            "Works in both TC and VW mode. Resistance increases when heated.",
            listOf(
                "Easy to use",
                "Durable",
                "No metallic taste like Kanthal",
                "Produces excellent flavor"
            ),
            listOf(
                "Can create 'hot spots' if coils are not properly spaced or heated"
            )
        ),
        WireEntry(
            "Nickel (Ni200)",
            "Nickel (Ni): 80%, Chromium (Cr): 20%",
            "Used exclusively in TC mode. Very low resistance. Changes resistance significantly with heat. Should not be dry-burned. Must be used with spaced coils. Not safe in VW mode.",
            listOf(
                "Extremely fast heat-up"
            ),
            listOf(
                "Highly sensitive to high temperatures",
                "Produces toxic fumes if overheated",
                "Does not hold shape well",
                "Contains allergenic nickel",
                "Must avoid if allergic to nickel"
            )
        ),
        WireEntry(
            "Titanium (Ti)",
            "Pure Titanium",
            "Used in Temperature Control (TC) mode. Becomes toxic at temperatures above 610°C / 1130°F.",
            listOf(
                "Easy to work with",
                "Clean flavor",
                "Long lifespan"
            ),
            listOf(
                "Hard to obtain",
                "Fire hazard at high temperatures",
                "Can release toxic compounds if overheated"
            )
        )
    )
}

/**
 * Manager to get localized Knowledge Data
 */
object KnowledgeDataManager {
    fun getDictionaryEntries(): List<DictionaryEntry> {
        return if (isGerman()) {
            KnowledgeDataDE.getDictionaryEntries()
        } else {
            KnowledgeDataEN.getDictionaryEntries()
        }
    }
    
    fun getFAQEntries(): List<FAQEntry> {
        return if (isGerman()) {
            KnowledgeDataDE.getFAQEntries()
        } else {
            KnowledgeDataEN.getFAQEntries()
        }
    }
    
    fun getWireEntries(): List<WireEntry> {
        return if (isGerman()) {
            KnowledgeDataDE.getWireEntries()
        } else {
            KnowledgeDataEN.getWireEntries()
        }
    }
    
    fun getTabNames(): Triple<String, String, String> {
        return if (isGerman()) {
            Triple("Lexikon", "Häufige Fragen", "Drähte")
        } else {
            Triple("Dictionary", "FAQ", "Wires")
        }
    }
    
    private fun isGerman(): Boolean {
        return Locale.getDefault().language == "de"
    }
}
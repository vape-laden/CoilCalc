package de.vapeladen.coilrechner.data.model

/**
 * Deutsche Version der Knowledge Zone Inhalte
 */
object KnowledgeDataDE {
    
    fun getDictionaryEntries(): List<DictionaryEntry> = listOf(
        DictionaryEntry(
            "Rebuildable Dripping Atomizer (RDA)",
            "Ein RDA ist ein selbstwickelbarer Verdampfer mit geringer Liquidkapazität, ausgezeichnetem Geschmack, großer Dampfproduktion und reichlich Airflow. Nutzer träufeln E-Liquid direkt auf die Coils. RDAs erlauben den Bau mehrerer Coils, sind kompakt und preisgünstig. Gelegentlich kann es zu Auslaufen kommen."
        ),
        DictionaryEntry(
            "E-Liquid",
            "E-Liquid ist eine Flüssigkeit für E-Zigaretten, die beim Erhitzen verdampft. Es enthält typischerweise Nikotin (0% bis 3,6%), Propylenglykol (PG), pflanzliches Glycerin (VG) und Aromen. Zusätzliche Inhaltsstoffe können Säureregulatoren, Wasser, Ethylmaltol oder Ethanol sein."
        ),
        DictionaryEntry(
            "Pflanzliches Glycerin (VG)",
            "VG ist eine dickflüssige Substanz, die für dichten Dampf mit mildem Geschmack sorgt. Es bewegt sich langsam zur Coil und wird für RDAs und moderne Sub-Ohm-Tanks empfohlen."
        ),
        DictionaryEntry(
            "Propylenglykol (PG)",
            "PG ist geschmeidiger als VG, transportiert Aromen besser, produziert aber weniger Dampf. Es ist ein Schlüsselbestandteil in E-Liquid-Formulierungen zum Ausbalancieren von Dampfproduktion und Geschmack."
        ),
        DictionaryEntry(
            "Nickel-Draht (Ni200)",
            "Ni200-Draht bietet niedrigen und temperaturempfindlichen Widerstand und eignet sich ausschließlich für den Temperature Control (TC) Dampfmodus."
        ),
        DictionaryEntry(
            "Luftloch (AH)",
            "Luftlöcher ermöglichen den Luftstrom durch den Verdampfer und beeinflussen Dampftemperatur und -dichte. Größere Löcher kühlen den Dampf, können aber die Dichte verringern."
        ),
        DictionaryEntry(
            "Aroma",
            "Aromen verleihen E-Liquids ihre charakteristischen Geschmacksrichtungen, von fruchtig bis tabakartig, und verbessern das Dampferlebnis."
        ),
        DictionaryEntry(
            "Nikotinbasis",
            "Nikotinbasis ist eine Lösung mit Nikotin in Konzentrationen bis zu 3,6% oder höher, gemischt mit VG und PG. Es existieren auch nikotinfreie Basen."
        ),
        DictionaryEntry(
            "Kurzschluss (S/C)",
            "Ein Kurzschluss entsteht, wenn unbeabsichtigter Kontakt einen niedrigohmigen elektrischen Pfad bildet, was potenziell Geräte beschädigen oder ein Sicherheitsrisiko darstellen kann."
        ),
        DictionaryEntry(
            "Edelstahl (SS)",
            "Edelstahldraht wird sowohl im Wattage- als auch im Temperature Control-Modus verwendet. Er ist langlebig, liefert guten Geschmack und ist vielseitig für den Coil-Bau."
        ),
        DictionaryEntry(
            "Sub-Ohm-Dampfen",
            "Sub-Ohm-Dampfen bezeichnet die Verwendung von Coils mit einem Widerstand unter 1,0 Ohm, was zu größeren Dampfwolken, wärmerem Dampf und intensivierem Geschmack führt."
        ),
        DictionaryEntry(
            "Temperaturkontrolle (TC)",
            "Der TC-Modus ermöglicht dem Gerät, die Coil-Temperatur durch Überwachung von Widerstandsänderungen zu regulieren, verhindert Dry Hits und verbessert die Konsistenz."
        ),
        DictionaryEntry(
            "Velocity Deck Stil",
            "Ein Velocity Deck ist ein Zwei-Pfosten-Coil-Bau-Deck mit großen Pfostenlöchern, das die Coil-Installation einfach und effizient für Dual-Coil-Builds macht."
        ),
        DictionaryEntry(
            "Spannungsabfall",
            "Spannungsabfall beschreibt den Leistungsverlust zwischen Akku und Verdampfer, der häufig bei mechanischen Mods auftritt. Niedrigerer Spannungsabfall bedeutet bessere Leistung."
        ),
        DictionaryEntry(
            "510-Anschluss",
            "Der 510-Anschluss ist ein Standard-Gewindesystem zum Befestigen von Verdampfern an Geräten. Er unterstützt austauschbare Drip Tips und bietet breite Kompatibilität."
        ),
        DictionaryEntry(
            "18650-Akkus",
            "18650-Akkus sind wiederaufladbare Lithium-Ionen-Zellen für Hochleistungsgeräte. Sie messen 18,6 mm × 65,2 mm und bieten typischerweise 1500–3400 mAh bei einer Nennspannung von 3,7 V."
        ),
        DictionaryEntry(
            "Ego-Style E-Zigaretten",
            "Ego-Style E-Zigaretten sind frühe Stift-förmige Dampfgeräte mit einfacher Bedienung, eingebauten Akkus und Kompatibilität mit Clearomizern."
        ),
        DictionaryEntry(
            "Variable Spannung (VV)",
            "VV-Geräte ermöglichen Nutzern die manuelle Anpassung der Ausgangsspannung für präzise Kontrolle über Dampfintensität, Geschmackswärme und Throat Hit."
        ),
        DictionaryEntry(
            "Variable Leistung (VW)",
            "VW-Geräte passen die Spannung automatisch an, um die vom Nutzer gewählte Leistung aufrechtzuerhalten. Dies führt zu konsistenterer Leistung unabhängig von Coil-Widerstandsänderungen."
        )
    )
    
    fun getFAQEntries(): List<FAQEntry> = listOf(
        FAQEntry(
            "Maximale Leistung bei Geräten einstellen",
            "Elektronische MODs erlauben Nutzern die Spannungsanpassung während der Coil-Widerstand konstant bleibt. Die Leistung (P) hängt von Spannung (U) und Widerstand (R) ab, gemäß dem Ohmschen Gesetz:\n\nP = U² / R\n\nDie maximalen Spannungs- und Wattgrenzen eines Geräts bestimmen den höchsten Coil-Widerstand, den es effektiv betreiben kann.\n\nBeispiel: Ein 50W-Gerät mit 10V-Limit kann bis zu 2 Ohm betreiben (da 10² / 50 = 2). Selbst wenn ein Gerät hohe Wattzahl bietet (z.B. 150W), muss es diese elektrischen Grenzen respektieren."
        ),
        FAQEntry(
            "Wahl zwischen Single- und Dual-Coils",
            "Dual-Coils scheinen doppelt soviel Dampf wie Single-Coils zu produzieren, aber die Leistung hängt ab von:\n\nWärmeverteilung\nLeistungsabgabe\nCoil-Oberfläche\nDocht-Sättigung\n\nMehr Oberfläche (mehr Coils, mehr Wicklungen, größerer Innendurchmesser) verteilt die Leistung über mehr Metall, reduziert Hitze pro Coil.\n\nBeispiel: Ein 20W-Gerät mit Dual-Coils liefert 10W pro Coil. Bei geringer Leistung ist eine Single-Coil effizienter.\n\nZu viel Hitze kann die Watte verbrennen, daher ist die richtige Balance entscheidend."
        ),
        FAQEntry(
            "Hat der Coil-Durchmesser Bedeutung?",
            "Ja. Der Coil-Durchmesser bestimmt Widerstand, Wärmekapazität und Airflow:\n\n• Gemessen in AWG (American Wire Gauge) oder Millimeter (mm)\n• Höherer AWG = dünnerer Draht = höherer Widerstand\n• Dickere Drähte erlauben mehr Stromfluss\n• Dickere Drähte speichern mehr Wärme und brauchen länger zum Aufheizen/Abkühlen\n\nDer Coil-Durchmesser beeinflusst Aufheizzeit, Geschmack, Dampfproduktion und Docht-Sättigung."
        ),
        FAQEntry(
            "Akkus: Serien- vs. Parallel-Konfiguration",
            "Serien-Konfiguration:\n• Spannung addiert sich (z.B. 3,7V + 3,7V = 7,4V)\n• Ampere-Limit bleibt gleich\n• Ermöglicht höherohmige Coils\n• Gesamtkapazität = (C1 × C2) / (C1 + C2)\n\nParallel-Konfiguration:\n• Spannung bleibt gleich (3,7V)\n• Ampere-Limit verdoppelt sich\n• Akkukapazität verdoppelt sich\n• Beispiel: zwei 2600mAh, 20A Akkus → 5200mAh Kapazität, 40A Limit\n\nBeide können dieselbe Wattzahl produzieren:\nSerie: 7,4V × 30A = 222W\nParallel: 3,7V × 60A = 222W"
        ),
        FAQEntry(
            "Mechanische vs. Elektronische Mods",
            "Mechanische Mods:\n• Reiner Akku + Schalter (keine Elektronik)\n• Keine Sicherheitsschutzmaßnahmen\n• Gängige Akkus: 18650, 26650, 18500, 18350\n• Leistung basiert auf Ohmschen Gesetz: P = U² / R\n• Nur für erfahrene Nutzer\n\nElektronische Mods:\n• Aktive Elektronik + LCD + Schutzmaßnahmen\n• Kurzschlussschutz, Abschaltung, Überhitzungsschutz\n• Variable Spannung (VV)\n• Variable Leistung (VW)\n• Temperaturkontrolle (TC)\n• Sicher für Einsteiger bei korrekter Akkuhandhabung"
        )
    )
    
    fun getWireEntries(): List<WireEntry> = listOf(
        WireEntry(
            "Kanthal (FeCrAl)",
            "Eisen (Fe): 60–75%, Chrom (Cr): 20–30%, Aluminium (Al): 4–7,5%",
            "Verwendet im Variable Wattage (VW) Modus. Hoher Widerstand. Ideale Wahl für Anfänger.",
            listOf(
                "Hält hohe Temperaturen aus",
                "Einfach zu formen und biegen",
                "Preiswert",
                "Stabiler Widerstand",
                "Kompatibel mit mechanischen Mods",
                "Behält Form gut bei",
                "Weit verbreitet"
            ),
            listOf(
                "Kann Geschmacksqualität bei Langzeitnutzung verlieren",
                "Kann metallischen Nachgeschmack bei Neuinstallation erzeugen",
                "Langsameres Aufheizen im Vergleich zu anderen Drähten"
            )
        ),
        WireEntry(
            "Nichrome (NiCr)",
            "Nickel (Ni): 80%, Chrom (Cr): 20%",
            "Verwendet im VW-Modus. Ändert Farbe beim Erhitzen. Niedriger Widerstand. Bleibt länger heiß.",
            listOf(
                "Heizt sehr schnell auf",
                "Produziert guten Geschmack",
                "Einfach zu verarbeiten",
                "Erschwinglich"
            ),
            listOf(
                "Enthält Nickel (kann Allergien auslösen)",
                "Niedrigerer Schmelzpunkt als Kanthal oder SS"
            )
        ),
        WireEntry(
            "Edelstahl (SS316)",
            "Nickel (Ni): 8%, Chrom (Cr): 18%, Molybdän (Mo): 2%",
            "Funktioniert sowohl im TC- als auch VW-Modus. Widerstand steigt beim Erhitzen.",
            listOf(
                "Einfach zu verwenden",
                "Langlebig",
                "Kein metallischer Geschmack wie Kanthal",
                "Produziert exzellenten Geschmack"
            ),
            listOf(
                "Kann 'Hot Spots' erzeugen wenn Coils nicht richtig platziert oder erhitzt werden"
            )
        ),
        WireEntry(
            "Nickel (Ni200)",
            "Nickel (Ni): 80%, Chrom (Cr): 20%",
            "Ausschließlich im TC-Modus verwendet. Sehr niedriger Widerstand. Ändert Widerstand deutlich mit Hitze. Sollte nicht trocken ausgeglüht werden. Muss mit beabstandeten Wicklungen verwendet werden. Nicht sicher im VW-Modus.",
            listOf(
                "Extrem schnelles Aufheizen"
            ),
            listOf(
                "Hochempfindlich gegenüber hohen Temperaturen",
                "Produziert giftige Dämpfe bei Überhitzung",
                "Behält Form nicht gut",
                "Enthält allergenes Nickel",
                "Muss vermieden werden bei Nickel-Allergie"
            )
        ),
        WireEntry(
            "Titan (Ti)",
            "Reines Titan",
            "Verwendet im Temperature Control (TC) Modus. Wird giftig bei Temperaturen über 610°C / 1130°F.",
            listOf(
                "Einfach zu verarbeiten",
                "Sauberer Geschmack",
                "Lange Lebensdauer"
            ),
            listOf(
                "Schwer zu beschaffen",
                "Brandgefahr bei hohen Temperaturen",
                "Kann giftige Verbindungen bei Überhitzung freisetzen"
            )
        )
    )
}
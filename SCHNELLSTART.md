# 🚀 SCHNELLSTART - CoilCalc

## ✅ Was wurde erstellt?

Eine vollständige Android-App mit:
- ✅ Coil-Widerstandsberechnung (Kanthal, Ni80, SS316L, Ni200, Titan)
- ✅ Wicklungsberechnung aus Zielwiderstand
- ✅ MTL/RDL/DL Presets
- ✅ Akku-Sicherheitschecks mit Farbampel
- ✅ Material Design 3 UI (Jetpack Compose)
- ✅ Deutsch/Englisch Lokalisierung
- ✅ Developer-Info-Screen (wie iOS-App)

## 📁 Projektstruktur

```
C:\Users\Sveta\Desktop\Vape Coil Rechner\
├── app/
│   ├── build.gradle.kts
│   ├── proguard-rules.pro
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/de/vapeladen/coilrechner/
│       │   ├── MainActivity.kt
│       │   ├── data/model/
│       │   ├── domain/
│       │   └── ui/
│       └── res/
│           ├── values/strings.xml (Deutsch)
│           ├── values-en/strings.xml (Englisch)
│           ├── values/themes.xml
│           └── xml/
├── build.gradle.kts
├── settings.gradle.kts
└── README.md (Vollständige Dokumentation)
```

## 🛠️ Nächste Schritte

### 1. Android Studio öffnen

1. Android Studio starten
2. **File → Open...**
3. Ordner auswählen: `C:\Users\Sveta\Desktop\Vape Coil Rechner`
4. **OK** klicken

### 2. Gradle Sync warten

- Android Studio lädt automatisch alle Dependencies
- Dauer: 2-5 Minuten beim ersten Mal
- ✅ Erfolgreich wenn: "BUILD SUCCESSFUL" in der Log

### 3. App testen

**Option A: Im Emulator**
1. **Tools → Device Manager**
2. Neues Gerät erstellen (z.B. Pixel 6, Android 14)
3. **Run → Run 'app'** (Shift+F10)

**Option B: Auf echtem Gerät**
1. USB-Debugging aktivieren (Entwickleroptionen)
2. Handy per USB verbinden
3. **Run → Run 'app'** (Shift+F10)

### 4. APK erstellen

**Debug-Version (zum Testen):**
- **Build → Build Bundle(s) / APK(s) → Build APK(s)**
- APK findet man in: `app/build/outputs/apk/debug/app-debug.apk`

**Release-Version (für Veröffentlichung):**
- **Build → Generate Signed Bundle / APK**
- Keystore erstellen oder vorhandenen wählen
- APK oder AAB (für Google Play Store) erstellen

## 🧪 App-Funktionen testen

### Test 1: MTL-Build berechnen
```
Material: Kanthal A1
AWG: 28 (0.321mm)
Innendurchmesser: 2.5 mm
Wicklungen: 7
→ Erwartung: ~1.2-1.5Ω (grüner Bereich)
```

### Test 2: Wicklungen für Zielwiderstand
```
Modus: "Widerstand → Wicklungen"
Material: Ni80
AWG: 26 (0.405mm)
Innendurchmesser: 3.0 mm
Zielwiderstand: 0.5 Ω
Coil-Typ: Dual
→ Erwartung: ~5-6 Wicklungen
```

### Test 3: Sicherheits-Check
```
Beliebige Coil-Eingabe
Leistung: 60W
Widerstand: 0.3Ω (errechnet)
Akku CDR: 20A
→ Erwartung: Grün oder Orange
   (I = √(60/0.3) ≈ 14.1A < 20A)
```

### Test 4: Developer-Info
```
1. Info-Icon (unten rechts) antippen
2. Link sollte Browser öffnen: https://vape-laden.de/
3. "Zurück" sollte zum Rechner zurückführen
```

### Test 5: Sprachumschaltung
```
1. Android-Systemsprache auf Englisch ändern
2. App neu starten
3. Alle Texte sollten Englisch sein
4. Zurück auf Deutsch → Texte wieder Deutsch
```

## ⚠️ Häufige Probleme & Lösungen

### "SDK location not found"
**Lösung:** 
- **File → Project Structure → SDK Location**
- Android SDK Path eintragen (z.B. `C:\Users\Sveta\AppData\Local\Android\Sdk`)

### "Gradle sync failed"
**Lösung:**
- **File → Invalidate Caches / Restart**
- Oder: **Build → Clean Project**, dann **Build → Rebuild Project**

### "Unresolved reference: R"
**Lösung:**
- **Build → Clean Project**
- **Build → Rebuild Project**
- Gradle Sync durchführen

### App startet nicht im Emulator
**Lösung:**
- Emulator neustarten
- Oder: Anderen Emulator erstellen (älteren Android-Version)

## 📱 Screenshots erstellen

1. App im Emulator starten
2. Verschiedene Screens aufrufen:
   - Hauptbildschirm (Rechner)
   - Ergebnis mit grünem Sicherheits-Check
   - Ergebnis mit orangem/rotem Warning
   - Developer-Info-Screen
3. **Kamera-Icon** im Emulator oder **Screenshot-Tool** verwenden

## 🎨 App anpassen

### Farben ändern
**Datei:** `app/src/main/java/de/vapeladen/coilrechner/ui/theme/Theme.kt`
```kotlin
primary = Color(0xFF6639B7),  // Lila → Deine Farbe
```

### Texte ändern
**Deutsch:** `app/src/main/res/values/strings.xml`
**Englisch:** `app/src/main/res/values-en/strings.xml`

### App-Name ändern
**Datei:** `app/src/main/res/values/strings.xml`
```xml
<string name="app_name">Dein App-Name</string>
```

### App-Icon ändern
1. Icon-Dateien vorbereiten (verschiedene Größen)
2. In Android Studio: **File → New → Image Asset**
3. Icon hochladen und generieren lassen

## 📦 Für Play Store vorbereiten

### 1. App-Signierung vorbereiten
```
Build → Generate Signed Bundle / APK
→ Android App Bundle wählen
→ Keystore erstellen (sicher aufbewahren!)
```

### 2. Wichtige Metadaten
- **App-Name:** CoilCalc
- **Kurzbeschreibung:** Präzise Coil-Berechnungen für E-Zigaretten / Precise coil calculations for vapers
- **Kategorie:** Tools / Lifestyle
- **Altersbeschränkung:** 18+ (je nach Land)
- **Screenshots:** Min. 2, empfohlen 4-8
- **Privacy Policy:** Erforderlich (auf vape-laden.de hosten)

### 3. Version erhöhen
**Datei:** `app/build.gradle.kts`
```kotlin
versionCode = 2       // Bei jedem Update +1
versionName = "1.0.1" // Semantische Versionierung
```

## 🔧 Zukünftige Erweiterungen

### Priorität 1 (einfach)
- [ ] Build-Speicherung (Room-DB bereits vorbereitet)
- [ ] Dark/Light Theme Switcher
- [ ] Mehr Coil-Typen (Clapton Details)

### Priorität 2 (mittel)
- [ ] Build-History mit Favoriten
- [ ] Export/Import von Builds
- [ ] Tutorial/Hilfe-Screens

### Priorität 3 (komplex)
- [ ] Cloud-Sync
- [ ] Community-Builds teilen
- [ ] Erweiterte Statistiken

## 📞 Hilfe bekommen

**Bei technischen Problemen:**
1. README.md lesen (vollständige Doku)
2. Android Studio Fehlermeldung kopieren
3. Google/Stack Overflow durchsuchen
4. GitHub Issues durchsehen (ähnliche Android-Projekte)

**Bei App-Design-Fragen:**
- iOS Liquid Calculator als Referenz verwenden
- Material Design Guidelines: https://m3.material.io/

## ✨ Tipps

1. **Regelmäßig committen:** Kleine Änderungen häufig speichern
2. **Testen auf echten Geräten:** Emulator ist gut, aber nicht perfekt
3. **Logs nutzen:** `Log.d("TAG", "message")` für Debugging
4. **Backup erstellen:** Vor großen Änderungen komplettes Projekt kopieren

## 🎯 Erfolgs-Checkliste

- [ ] Android Studio installiert und konfiguriert
- [ ] Projekt öffnet ohne Fehler
- [ ] Gradle Build erfolgreich
- [ ] App läuft im Emulator
- [ ] Alle 5 Tests bestanden
- [ ] Screenshots erstellt
- [ ] APK generiert
- [ ] Auf Testgerät installiert

---

**Viel Erfolg! 🚀**

Bei Fragen: Dokumentation in README.md lesen oder Android Studio Fehlermeldungen googeln.
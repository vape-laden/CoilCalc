# 🎨 App-Logo in Android Studio hinzufügen

## 📋 Schritt-für-Schritt Anleitung

### 1️⃣ Logo vorbereiten

**Benötigte Größe:** Mindestens **512 x 512 px** (besser 1024 x 1024 px)  
**Format:** PNG oder JPG  
**Hintergrund:** Für beste Ergebnisse mit transparentem Hintergrund (PNG)

Dein Logo ist bereits perfekt: **COIL CALC** mit Coil-Grafik auf lila Hintergrund

### 2️⃣ In Android Studio öffnen

1. **Android Studio** starten
2. Projekt öffnen: `C:\Users\Sveta\Desktop\Vape Coil Rechner`
3. Warten bis Gradle-Sync fertig ist

### 3️⃣ Image Asset Tool öffnen

**Methode 1: Über Menü**
```
File → New → Image Asset
```

**Methode 2: Über Projekt-Explorer**
1. Im Projekt-Explorer links: `app` → rechtsklick
2. `New → Image Asset`

### 4️⃣ Logo konfigurieren

Im **Image Asset Studio** Fenster:

**Settings:**
- **Icon Type:** `Launcher Icons (Adaptive and Legacy)`
- **Asset Type:** `Image`
- **Path:** Klick auf Ordner-Icon → wähle dein Logo aus

**Vorschau:**
- Du siehst rechts verschiedene Größen (mdpi, hdpi, xhdpi, xxhdpi, xxxhdpi)
- Android zeigt auch runde und quadratische Varianten

**Optionen:**
- **Trim:** ☑️ Automatisch zuschneiden
- **Resize:** 100% (oder anpassen nach Bedarf)
- **Shape:** None (da dein Logo bereits den richtigen Rahmen hat)
- **Background Layer:**
  - **Background Color:** #6639B7 (dein Lila-Ton)

### 5️⃣ Icon generieren

1. Klick **Next**
2. Android Studio zeigt Vorschau aller Icon-Größen
3. Klick **Finish**

**Fertig!** Android Studio erstellt automatisch:
```
app/src/main/res/
├── mipmap-mdpi/
│   ├── ic_launcher.png
│   └── ic_launcher_round.png
├── mipmap-hdpi/
│   ├── ic_launcher.png
│   └── ic_launcher_round.png
├── mipmap-xhdpi/
│   ├── ic_launcher.png
│   └── ic_launcher_round.png
├── mipmap-xxhdpi/
│   ├── ic_launcher.png
│   └── ic_launcher_round.png
└── mipmap-xxxhdpi/
    ├── ic_launcher.png
    └── ic_launcher_round.png
```

### 6️⃣ Prüfen

**AndroidManifest.xml** wird automatisch aktualisiert zu:
```xml
android:icon="@mipmap/ic_launcher"
android:roundIcon="@mipmap/ic_launcher_round"
```

Wenn nicht automatisch geändert, musst du manuell diese Zeile korrigieren.

### 7️⃣ App neu bauen

1. **Build → Clean Project**
2. **Build → Rebuild Project**
3. App auf Gerät/Emulator starten
4. Das neue Logo erscheint auf dem Home Screen! 🎉

## 🎨 Alternative: Manuell erstellen

Falls Image Asset Studio nicht funktioniert:

### Option A: Adaptive Icon Generator (Online)

1. Gehe zu: **https://icon.kitchen/**
2. Logo hochladen
3. Background Color: `#6639B7`
4. Download als ZIP
5. Entpacken und Dateien nach `app/src/main/res/` kopieren

### Option B: App Icon Generator

1. **https://www.appicon.co/**
2. Logo hochladen
3. Platform: `Android` wählen
4. Download und in das Projekt kopieren

## 📱 Icon-Typen erklärt

**ic_launcher.png:**
- Normale App-Icons (die meisten Geräte)

**ic_launcher_round.png:**
- Runde Icons (Samsung, Pixel-Geräte mit runden Masken)

**Adaptive Icons (Android 8+):**
- Foreground (dein Logo)
- Background (lila Farbe)
- System schneidet zu rund/quadratisch/etc.

## ✅ Erfolgskontrolle

Nach dem Rebuild:
- [ ] Logo erscheint im App-Drawer
- [ ] Logo hat richtige Farben (lila + weiß)
- [ ] Logo ist scharf (nicht verpixelt)
- [ ] Runde Variante sieht gut aus
- [ ] Logo passt zu deinem Branding

## 💡 Tipps

**Für beste Ergebnisse:**
- Logo sollte **zentriert** sein
- **Keine wichtigen Details** ganz am Rand (wegen Abrundung)
- **Hoher Kontrast** zwischen Logo und Hintergrund
- **1024x1024 px** als Quell-Datei verwenden

**Test auf verschiedenen Geräten:**
- Samsung (runde Icons)
- Pixel (verschiedene Formen)
- Andere Hersteller (quadratische Icons)

## 🔄 Logo später ändern

Einfach den Prozess wiederholen:
1. `File → New → Image Asset`
2. Neues Logo wählen
3. `Finish` → überschreibt alte Icons
4. Rebuild

---

**Viel Erfolg! Dein CoilCalc Logo sieht großartig aus!** 🎨✨
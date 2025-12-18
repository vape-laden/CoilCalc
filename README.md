# CoilCalc - Advanced Vape Coil Calculator

> **Professional vape coil calculator for Android** - Developed by [Vape-Laden](https://vape-laden.de)

CoilCalc is a professional Android application designed for precise calculation of vape coil parameters. This open-source tool provides vapers with accurate technical specifications for their custom coil builds.

## Overview

CoilCalc offers comprehensive calculations for coil resistance, power requirements, and safety parameters. The application supports multiple wire materials and provides real-time feedback on build specifications.

## Features

- **Precise Coil Calculations**: Calculate resistance, wattage, and voltage for custom coil builds
- **Multiple Wire Materials**: Support for various wire types including Kanthal, Nichrome, Stainless Steel, and more
- **Safety Indicators**: Real-time safety warnings and recommendations based on battery specifications
- **Battery Life Estimation**: Calculate expected battery duration based on build specifications
- **Unit Converters**: Built-in converters for common vaping measurements
- **Knowledge Base**: Educational content about coil building and vaping safety
- **Multi-language Support**: Available in English, German, and French

## Technical Stack

- **Platform**: Android (Kotlin)
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM (Model-View-ViewModel)
- **Build System**: Gradle with Kotlin DSL

## Project Structure

```
app/
├── src/main/
│   ├── java/de/vapeladen/coilrechner/
│   │   ├── data/model/          # Data models for calculations
│   │   ├── domain/              # Business logic and calculators
│   │   ├── ui/                  # User interface components
│   │   │   ├── calculator/      # Main calculator screen
│   │   │   ├── knowledge/       # Educational content
│   │   │   ├── tools/           # Additional utilities
│   │   │   └── theme/           # App theming
│   │   └── MainActivity.kt
│   └── res/                     # Resources (layouts, strings, images)
```

## Development

### Prerequisites

- Android Studio Arctic Fox or newer
- Android SDK 24+
- Gradle 8.0+
- Kotlin 1.9+

### Building the Project

```bash
# Clone the repository
git clone https://github.com/vape-laden/CoilCalc.git

# Navigate to project directory
cd CoilCalc

# Build debug APK
./gradlew assembleDebug

# Build release AAB
./gradlew bundleRelease
```

### Running Tests

```bash
./gradlew test
```

## About Vape-Laden

CoilCalc is developed and maintained by [Vape-Laden](https://vape-laden.de), a team dedicated to providing high-quality tools and resources for the vaping community. Our goal is to promote safe vaping practices through education and precision tools.

This application represents our commitment to transparency and community-driven development. All core calculation algorithms are open-source, allowing for peer review and continuous improvement.

**Visit us:** [https://vape-laden.de](https://vape-laden.de)

## License

This project is publicly available as part of our commitment to the vaping community. The application is also available on the Google Play Store.

## Repository Information

- **Organization**: [vape-laden](https://github.com/vape-laden) (VortexCom UG (haftungsbeschränkt))
- **Project**: CoilCalc
- **Type**: Native Android Application
- **Status**: Active Development

## Technical Documentation

### Calculation Methodology

CoilCalc uses industry-standard formulas for electrical resistance calculations:

- **Resistance**: Based on wire material resistivity, length, and cross-sectional area
- **Power**: Calculated using Ohm's Law (P = V²/R or P = I²R)
- **Current**: Derived from voltage and resistance (I = V/R)
- **Heat Flux**: Considers wire surface area and power delivery

### Supported Wire Materials

- Kanthal A1
- Nichrome (Ni80, Ni90)
- Stainless Steel (SS316L, SS304, SS317L)
- Nickel (Ni200)
- Titanium (Grade 1)

## Code Quality

The project maintains high code quality standards:

- Kotlin coding conventions
- MVVM architectural pattern
- Comprehensive input validation
- Safety-first approach to calculations
- Regular dependency updates

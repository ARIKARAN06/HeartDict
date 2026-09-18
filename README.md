# HeartDict ❤️

HeartDict is an Android application for heart disease prediction using a machine learning model.

The project connects a Python-trained model with an Android app by converting a saved `.sav` model into ONNX format.

> HeartDict is an educational project. Its predictions are not a medical diagnosis or a substitute for professional medical advice.

## Overview

The repository contains two main components:

- **Python:** A script that loads the saved machine learning model using `pickle` and converts it to ONNX using `skl2onnx`.
- **Android:** The Android Studio project containing the application's source code and model integration.

## Features

- Android interface for entering the model's required input values.
- Heart disease prediction using a machine learning model.
- Python-to-ONNX model conversion.
- Integration of the exported ONNX model into the Android application.

## Technologies

- Python
- pickle
- scikit-learn
- skl2onnx
- ONNX
- Android Studio
- Gradle

## Project Structure

The following layout is recommended. Filenames and paths should match the files committed to this repository.

```text
HeartDict/
├── README.md
├── .gitattributes
│
├── android/                         # Android Studio project
│   ├── .gitignore
│   ├── .idea/                       # IDE settings currently tracked
│   ├── app/
│   │   ├── .gitignore
│   │   ├── build.gradle.kts
│   │   ├── proguard-rules.pro
│   │   └── src/
│   │       ├── androidTest/         # Instrumented tests
│   │       ├── test/                # Unit tests
│   │       └── main/
│   │           ├── AndroidManifest.xml
│   │           ├── ic_launcher-playstore.png
│   │           ├── assets/
│   │           │   └── heart_disease_model.onnx
│   │           ├── java/com/firstapp/diseasepredictor/
│   │           │   ├── MainActivity.kt
│   │           │   └── HeartDiseasePredictor.kt
│   │           └── res/
│   │               ├── drawable/
│   │               ├── layout/
│   │               │   └── activity_main.xml
│   │               ├── mipmap-*/    # Launcher icons
│   │               ├── values/
│   │               ├── values-night/
│   │               └── xml/
│   ├── build.gradle.kts
│   ├── settings.gradle.kts
│   ├── gradle.properties
│   ├── gradle/
│   │   ├── libs.versions.toml
│   │   └── wrapper/
│   │       ├── gradle-wrapper.jar
│   │       └── gradle-wrapper.properties
│   ├── gradlew
│   └── gradlew.bat
│
├── app/                             # Installable Android app
│   └── HeartDict.apk
│
└── python/                          # Python scripts and model files
    ├── app.py
    ├── check_model.py
    ├── covert_model.py
    ├── test.py
    ├── heart_disease_model.sav
    ├── heart_disease_model.onnx
    └── requirements.txt.txt
```

- **android/** contains the Android application source, resources,
  ONNX model, tests, and Gradle configuration.
- **app/** contains the downloadable `HeartDict.apk`.
- **python/** contains the Python scripts and the original and
  converted model files.

Android projects may use `.gradle` files instead of `.gradle.kts`. Keep the build files generated for your existing project.

## How It Works

1. Load the saved `.sav` model in Python.
2. Define the input format expected by the model.
3. Convert the model to ONNX using `skl2onnx`.
4. Include the exported ONNX model in the Android project.
5. Collect and prepare the user's input in the app.
6. Run the model and display its prediction.

The Android input order, data types, and preprocessing must match those used during training.

## Getting Started

### Prerequisites

- Python and pip
- Android Studio
- An Android device or emulator
- The saved `.sav` model
- The project's Android and Python source files

### 1. Download the Repository

Clone this repository using its GitHub URL, or download and extract its ZIP file.

### 2. Set Up Python

Open a terminal in the `python` folder:

```bash
cd python
python -m venv venv
```

Activate the virtual environment.

**Windows PowerShell:**

```powershell
.\venv\Scripts\Activate.ps1
```

**Windows Command Prompt:**

```bat
venv\Scripts\activate.bat
```

**macOS or Linux:**

```bash
source venv/bin/activate
```

Install the dependencies:

```bash
python -m pip install -r requirements.txt
```

### 3. Python Dependencies

The `requirements.txt` file contains:

```txt
scikit-learn
skl2onnx
onnx
```

`pickle` is included with Python and does not need to be installed.

Use the same scikit-learn version that was used to save the original model. Record the tested dependency versions for reproducible conversion.

### 4. Convert the Model

Ensure that the conversion script points to the correct `.sav` file and output location.

If your script is named `convert_to_onnx.py`, run:

```bash
python convert_to_onnx.py
```

Verify that the exported ONNX file exists and that its input and output definitions match the Android integration.

Only load pickle model files from trusted sources.

### 5. Run the Android App

1. Open Android Studio.
2. Select **Open** and choose the `android` project folder.
3. Allow Gradle to sync.
4. Place the exported ONNX model at the location expected by the app, such as `app/src/main/assets/`.
5. Connect an Android device or start an emulator.
6. Build and run the application.

The ONNX inference dependency belongs in the Android project's Gradle configuration. Python conversion packages do not provide the Android inference runtime.

## Model Integration Notes

To keep Python and Android predictions consistent:

- Use the same feature order.
- Match the input tensor shape and data type.
- Apply the same scaling, encoding, and other preprocessing.
- Check the ONNX input and output names.
- Map output labels according to the trained model.
- Compare Python and Android predictions on the same sample inputs.

If preprocessing was saved separately from the model, it must also be reproduced in the app or included in the exported pipeline.

## Evaluation

Model accuracy, dataset details, and validation results are not documented in this README yet.

Add verified results from the actual training and evaluation workflow before making performance claims.

## Limitations

- Predictions depend on the training data, model quality, and correctness of the input.
- Incorrect feature ordering or preprocessing can produce unreliable results.
- Clinical validation has not been established.
- The app should not be used to make medical decisions.

## Future Improvements

- Improve input validation and error messages.
- Refine the app interface.
- Document the training dataset and evaluation results.
- Add automated checks comparing Python and Android predictions.
- Explore chatbot integration as a future enhancement.

## Files Excluded from Git

Generated files and local settings should remain outside the repository:

```gitignore
# Python
venv/
.venv/
__pycache__/
*.pyc

# Android Studio
.idea/
*.iml
.gradle/
build/
local.properties

# Local secrets and signing keys
.env
*.jks
*.keystore
```

Keep the Gradle wrapper, build configuration, source code, and required model files in the repository.

## Author

**Arikaran A**

- GitHub: [ARIKARAN06](https://github.com/ARIKARAN06)
- LinkedIn: [Arikaran Aruljothi](https://linkedin.com/in/arikaran-aruljothi)

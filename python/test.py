import pickle
import numpy as np
import onnxruntime as ort
import warnings
warnings.filterwarnings("ignore")

# -----------------------------
# Test input
# -----------------------------
input_data = np.array(
    [[63,1,3,145,233,1,0,150,0,2.3,0,0,1]],
    dtype=np.float32
)

# -----------------------------
# Load original .sav model
# -----------------------------
with open("heart_disease_model.sav", "rb") as file:
    model = pickle.load(file)

python_prediction = model.predict(input_data)

print("Python (.sav) prediction:", python_prediction)


# -----------------------------
# Load ONNX model
# -----------------------------
session = ort.InferenceSession("heart_disease_model.onnx")

input_name = session.get_inputs()[0].name

onnx_prediction = session.run(
    None,
    {input_name: input_data}
)

print("ONNX prediction:", onnx_prediction)
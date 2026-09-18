import pickle
import numpy as np
from skl2onnx import to_onnx
from skl2onnx.common.data_types import FloatTensorType

with open("heart_disease_model.sav","rb") as file:
    model = pickle.load(file)
print("Model Loaded:")
print(model)

initial_type = [
    ("float_input",FloatTensorType([None,13]))
]

onnx_model = to_onnx(
    model,
    initial_types=initial_type
)

with open("heart_disease_model.onnx","wb") as file:
    file.write(onnx_model.SerializeToString())
print("ONNX model created successfully")

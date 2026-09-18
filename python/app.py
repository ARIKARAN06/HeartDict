import pickle
import os
import numpy as np
from flask import Flask,request,jsonify
import warnings
warnings.filterwarnings("ignore")



app = Flask(__name__)
working_dir = os.path.dirname(os.path.abspath(__file__))

model_path = os.path.join(working_dir,"heart_disease_model.sav")

with open(model_path,"rb") as file:
    model = pickle.load(file)

print("Model loaded successfully")
print(model)

@app.route("/")
def home():
    return "Heart Disease prediction API is running"
@app.route("/predict",methods=["POST"])
def predict():
    data = request.get_json()

    age = data['age']
    sex = data['sex']
    cp = data['cp']
    trestbps = data['trestbps']
    chol = data['chol']
    fbs = data['fbs']
    restecg = data['restecg']
    thalach = data['thalach']
    exang = data['exang']
    oldpeak = data['oldpeak']
    slope = data['slope']
    ca = data['ca']
    thal = data['thal']

    input_data = (
        age,
        sex,
        cp,
        trestbps,
        chol,
        fbs,
        restecg,
        thalach,
        exang,
        oldpeak,
        slope,
        ca,
        thal
    )

    input_data_numpy_array = np.asarray(input_data)
    input_data_reshape = input_data_numpy_array.reshape(1,-1)
    prediction = model.predict(input_data_reshape)
    if prediction[0] == 0:
        result = "HEART DISEASE: NEGATIVE"
    else:
        result = "HEART DISEASE: POSITIVE"
    return jsonify({
            "prediction":int(prediction[0]),
            "result":result
        })

if __name__ == "__main__":
    app.run(debug=True)
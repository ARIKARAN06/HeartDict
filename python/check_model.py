import pickle

with open("heart_disease_model.sav", "rb") as file:
    model = pickle.load(file)

print("Model type:")
print(type(model))

print("\nModel:")
print(model)

print("\nModel parameters:")
if hasattr(model, "get_params"):
    print(model.get_params())

print("\nHas steps:")
if hasattr(model, "steps"):
    print(model.steps)
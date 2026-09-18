package com.firstapp.diseasepredictor

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import android.graphics.Rect
import android.view.View

import android.widget.Button
import android.widget.EditText
import android.widget.ScrollView
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val scrollView = findViewById<ScrollView>(R.id.scrollView)

        ViewCompat.setOnApplyWindowInsetsListener(scrollView) { view, insets ->

            val imeInsets = insets.getInsets(
                WindowInsetsCompat.Type.ime()
            )

            view.setPadding(
                view.paddingLeft,
                view.paddingTop,
                view.paddingRight,
                imeInsets.bottom
            )

            insets
        }

        val ageInput = findViewById<EditText>(R.id.ageInput)
        val sexInput = findViewById<EditText>(R.id.sexInput)
        val cpInput = findViewById<EditText>(R.id.cpInput)
        val trestbpsInput = findViewById<EditText>(R.id.trestbpsInput)
        val cholInput = findViewById<EditText>(R.id.cholInput)
        val fbsInput = findViewById<EditText>(R.id.fbsInput)
        val restecgInput = findViewById<EditText>(R.id.restecgInput)
        val thalachInput = findViewById<EditText>(R.id.thalachInput)
        val exangInput = findViewById<EditText>(R.id.exangInput)
        val oldpeakInput = findViewById<EditText>(R.id.oldpeakInput)
        val slopeInput = findViewById<EditText>(R.id.slopeInput)
        val caInput = findViewById<EditText>(R.id.caInput)
        val thalInput = findViewById<EditText>(R.id.thalInput)

        val predictor = HeartDiseasePredictor(this)

        val predictButton = findViewById<Button>(R.id.predictButton)

        val resultText = findViewById<TextView>(R.id.resultText)

        predictButton.setOnClickListener {
            try{
                val age = ageInput.text.toString().toFloat()
                val sex = sexInput.text.toString().toFloat()
                val cp = cpInput.text.toString().toFloat()
                val trestbps = trestbpsInput.text.toString().toFloat()
                val chol = cholInput.text.toString().toFloat()
                val fbs = fbsInput.text.toString().toFloat()
                val restecg = restecgInput.text.toString().toFloat()
                val thalach = thalachInput.text.toString().toFloat()
                val exang = exangInput.text.toString().toFloat()
                val oldpeak = oldpeakInput.text.toString().toFloat()
                val slope = slopeInput.text.toString().toFloat()
                val ca = caInput.text.toString().toFloat()
                val thal = thalInput.text.toString().toFloat()

                val inputData = floatArrayOf(
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

                val prediction = predictor.predict(inputData)

                if (prediction == 1L){
                    resultText.text = "HEART DISEASE: POSITIVE"
                } else {
                    resultText.text = "HEART DISEASE: NEGATIVE"
                }
            } catch (e: NumberFormatException){
                resultText.text = "please Enter all values Correctly"
            }
        }
    }
}
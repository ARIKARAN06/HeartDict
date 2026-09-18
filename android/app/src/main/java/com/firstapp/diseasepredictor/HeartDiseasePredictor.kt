package com.firstapp.diseasepredictor

import android.content.Context
import ai.onnxruntime.OnnxTensor
import ai.onnxruntime.OrtEnvironment
import ai.onnxruntime.OrtSession
import java.nio.FloatBuffer

class HeartDiseasePredictor(private val context: Context) {

    private val ortEnvironment = OrtEnvironment.getEnvironment()

    private val ortSession: OrtSession

    init {
        val modelBytes = context.assets
            .open("heart_disease_model.onnx")
            .readBytes()

        ortSession = ortEnvironment.createSession(modelBytes)
    }

    fun predict(input: FloatArray): Long {

        val inputTensor = OnnxTensor.createTensor(
            ortEnvironment,
            FloatBuffer.wrap(input),
            longArrayOf(1, 13)
        )

        val inputName = ortSession.inputNames.first()

        val result = ortSession.run(
            mapOf(inputName to inputTensor)
        )

        val prediction = result[0].value as LongArray

        inputTensor.close()
        result.close()

        return prediction[0]
    }
}
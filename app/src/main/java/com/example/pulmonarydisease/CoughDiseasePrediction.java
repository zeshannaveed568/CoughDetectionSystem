package com.example.pulmonarydisease;

import org.tensorflow.lite.Interpreter;

import java.nio.MappedByteBuffer;

public class CoughDiseasePrediction {

    private final Interpreter tflite;

    public CoughDiseasePrediction(MappedByteBuffer tfliteModel) {
        tflite = new Interpreter(tfliteModel);
    }

    public int predict(float[][][] audioData) {
        float[][] output = new float[1][5];
        tflite.run(audioData, output);
        int maxIndex = 0;
        for (int i = 1; i < 5; i++) {
            if (output[0][i] > output[0][maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }



}

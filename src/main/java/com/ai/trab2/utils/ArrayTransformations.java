package com.ai.trab2.utils;

public class ArrayTransformations {

    public static int[][] arrayToMatrix(int[] arrayToTransform) {
        int[][] result = new int[3][3];
        for(int i = 0; i < arrayToTransform.length; i++) {
            result[i/3][i%3] = arrayToTransform[i];
        }
        return result;
    }

    public static int[] matrixToArray(int[][] matrixToTransform) {
        int[] result = new int[9];
        for(int i = 0; i < result.length; i++) {
            result[i] = matrixToTransform[i/3][i%3];
        }
        return result;
    }

    public static int arrayToMatrixColumn(int arrPos) {
        return arrPos / 3;
    }
    public static int arrayToMatrixRow(int arrPos) {
        return arrPos % 3;
    }
}

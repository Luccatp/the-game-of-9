package com.ai.trab2.utils;

import java.util.Arrays;

public class ArrayTransformations {

    public static int[][] arrayToMatrix(int[] arrayToTransform) {
        if (arrayToTransform == null || arrayToTransform.length != 9) {
            throw new IllegalArgumentException("Invalid input array for matrix conversion");
        }

        int[][] result = new int[3][3];
        for (int i = 0; i < arrayToTransform.length; i++) {
            result[i / 3][i % 3] = arrayToTransform[i];
        }
        return result;
    }

    public static int[] matrixToArray(int[][] matrixToTransform) {
        if (matrixToTransform == null || matrixToTransform.length != 3 || matrixToTransform[0].length != 3) {
            throw new IllegalArgumentException("Invalid input matrix for array conversion");
        }

        int[] result = new int[9];
        for (int i = 0; i < result.length; i++) {
            result[i] = matrixToTransform[i / 3][i % 3];
        }
        return result;
    }

    public static int[] findIndexInMatrix(int[][] matrix, int numberToFind) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == numberToFind) {
                    return new int[]{i, j}; // Return the indices if the number is found
                }
            }
        }
        return null; // Return null if the number is not found
    }

    public static boolean compareMatrixes(int[][] firstMatrix, int[][] secondMatrix) {
        boolean isEqual = true;

        int row1 = firstMatrix.length;
        int col1 = firstMatrix[0].length;


        int row2 = secondMatrix.length;
        int col2 = secondMatrix[0].length;

        if (row1 != row2 || col1 != col2) {
            return false;
        }
        for (int i = 0; i < row1; i++) {
            for (int j = 0; j < col1; j++) {
                if (firstMatrix[i][j] != secondMatrix[i][j]) {
                    isEqual = false;
                    break;
                }
            }
        }
        return isEqual;
    }

    public  static  void printMatrix(int[][] matrix) {
        for(int[] array : matrix)
            System.out.println(Arrays.toString(array));
    }

    public static int arrayToMatrixColumn(int arrPos) {
        return arrPos / 3;
    }
    public static int arrayToMatrixRow(int arrPos) {
        return arrPos % 3;
    }
}

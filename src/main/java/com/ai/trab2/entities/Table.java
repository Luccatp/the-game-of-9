package com.ai.trab2.entities;

import com.ai.trab2.utils.ArrayTransformations;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

@Getter
@AllArgsConstructor
public class Table implements Comparable<Table> {

    private int[][] matrix;

    public int[][] swapUp() {
        int[] tableArray = ArrayTransformations.matrixToArray(matrix);
        int zeroIndex = ArrayTransformations.findIndexInArray(tableArray, 0);
        int indexToSwap = zeroIndex - 3;
        if(indexToSwap > 0) {
            tableArray[zeroIndex] = tableArray[indexToSwap];
            tableArray[indexToSwap] = 0;
        }

        return ArrayTransformations.arrayToMatrix(tableArray);
    }

    public int[][] swapDown() {
        int[] tableArray = ArrayTransformations.matrixToArray(matrix);
        int zeroIndex = ArrayTransformations.findIndexInArray(tableArray, 0);
        int indexToSwap = zeroIndex + 3;
        if(indexToSwap < tableArray.length) {
            tableArray[zeroIndex] = tableArray[indexToSwap];
            tableArray[indexToSwap] = 0;
        }

        return ArrayTransformations.arrayToMatrix(tableArray);
    }

    public int[][] swapRight() {
        int[] tableArray = ArrayTransformations.matrixToArray(matrix);
        int zeroIndex = ArrayTransformations.findIndexInArray(tableArray, 0);
        int indexToSwap = zeroIndex + 1;

        if(indexToSwap < tableArray.length) {
            tableArray[zeroIndex] = tableArray[indexToSwap];
            tableArray[indexToSwap] = 0;
        }

        return ArrayTransformations.arrayToMatrix(tableArray);
    }

    public int[][] swapLeft() {
        int[] tableArray = ArrayTransformations.matrixToArray(matrix);
        int zeroIndex = ArrayTransformations.findIndexInArray(tableArray, 0);
        int indexToSwap = zeroIndex - 1;
        if(indexToSwap > 0) {
            tableArray[zeroIndex] = tableArray[indexToSwap];
            tableArray[indexToSwap] = 0;
        }

        return ArrayTransformations.arrayToMatrix(tableArray);
    }

    @Override
    public int compareTo(Table table) {
        int[] tableArray = ArrayTransformations.matrixToArray(table.getMatrix());
        int[] thisTable = ArrayTransformations.matrixToArray(this.getMatrix());
        return Arrays.compare(tableArray, thisTable);
    }
}

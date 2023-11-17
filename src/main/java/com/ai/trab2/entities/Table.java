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
public class Table {
//     private static final int [] config1 = new int[]{1,2,3,4,5,6,0,7,8};
//     private static final int [] config2 = new int[]{1,3,0,4,2,5,7,8,6};
//     private static final int [] config3 = new int[]{1,3,5,2,6,0,4,7,8};
//     private static final int [] config4 = new int[]{1,8,3,4,2,6,7,5,0};
//     private static final int [] config5 = new int[]{1,2,3,7,0,6,4,8,5};
     private static final ArrayList<int[]> configs =
        (ArrayList<int[]>) Arrays.asList(
           new int[]{1,2,3,4,5,6,0,7,8},
           new int[]{1,3,0,4,2,5,7,8,6},
           new int[]{1,3,5,2,6,0,4,7,8},
           new int[]{1,8,3,4,2,6,7,5,0},
           new int[]{1,2,3,7,0,6,4,8,5});
    private final int[] selectedConfig;
    private int[] matrix;
    private LinkedList<Integer> logs;

    private static final int[] expectedArray = {1,2,3,4,5,6,7,8,9,0};

     public Table(int config) {
          selectedConfig = configs.get(config);
          matrix = Arrays.copyOf(selectedConfig, selectedConfig.length);
          logs = new LinkedList<>();
     }

     public void swap(int number) {
         if(isPositionValidForSwap(number)) {
             int numberPosition = Arrays.binarySearch(matrix, number);
             matrix[Arrays.binarySearch(matrix, number)] = 0;
             matrix[numberPosition] = number;
             logs.add(number);
         }
     }

     public int[] getZeroPosition() {
         int numberPosition = Arrays.binarySearch(matrix, 0);
        return new int[]{12};
     }
    private boolean isPositionValidForSwap(int number) {
        int zeroPosition = Arrays.binarySearch(matrix, 0);
        int left = zeroPosition - 1;
        int right = zeroPosition + 1;
        int down = zeroPosition + 3;
        int up = zeroPosition - 3;

        if(left > 0 && right < matrix.length && down < matrix.length && up > 0) {
            return number != 0 ||
                    matrix[left] == number ||
                    matrix[right] == number ||
                    matrix[up] == number ||
                    matrix[down] == number;
        }
        return false;
    }
}

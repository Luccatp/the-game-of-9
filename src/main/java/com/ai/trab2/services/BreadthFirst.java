package com.ai.trab2.services;

import com.ai.trab2.entities.Node;
import com.ai.trab2.services.interfaces.BreadthFirstInterface;
import com.ai.trab2.utils.ArrayTransformations;

import java.util.*;

public class BreadthFirst implements BreadthFirstInterface {

    @Override
    public void solveBreadthFirst(int[][] initialMatrix, int[][] finalMatrix, int x, int y) {
        Node root = new Node(initialMatrix, null);
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            if (isEqual(currentNode.getValue(), finalMatrix)) {
                // Solution found, print or return the solution
                printSolution(currentNode);
                return;
            }

            expandNode(currentNode);
            queue.addAll(currentNode.getChildren());
        }

        System.out.println("No solution found.");
    }

    private boolean isEqual(int[][] matrix1, int[][] matrix2) {
        // Implement logic to check if two matrices are equal
        // This depends on your specific requirements for equality
        // For simplicity, we'll assume the matrices are equal if all corresponding elements are equal
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1[i].length; j++) {
                if (matrix1[i][j] != matrix2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private void expandNode(Node node) {
        // Implement logic to generate child nodes based on the game rules
        // For the 9-puzzle game, you need to create child nodes by swapping the empty space with adjacent tiles

        // Example code (you need to adapt it based on the actual game rules)
        int[][] currentValue = node.getValue();
        int[] zeroPosition = node.getZeroPosition();
        if (zeroPosition[0] - 1 >= 0) {
            int up = currentValue[zeroPosition[0] - 1][zeroPosition[1]];
            int[][] upMatrix = swap(currentValue, up);
            node.addChild(upMatrix);
        }
        if(zeroPosition[0] + 1 < currentValue.length) {
            int down = currentValue[zeroPosition[0] + 1][zeroPosition[1]];
            int[][] downMatrix = swap(currentValue, down);
            node.addChild(downMatrix);
        }
        if(zeroPosition[1] - 1 >= 0) {
            int left = currentValue[zeroPosition[0]][zeroPosition[1] - 1];
            int[][] leftMatrix = swap(currentValue, left);
            node.addChild(leftMatrix);
        }
        if(zeroPosition[1] + 1 < currentValue[0].length) {
            int right = currentValue[zeroPosition[0]][zeroPosition[1] + 1];
            int[][] rightMatrix = swap(currentValue, right);
            node.addChild(rightMatrix);
        }
    }

    private int[][] swap(int[][] matrix, int numberToSwap) {
       int[] zeroIndexes = ArrayTransformations.findIndexInMatrix(matrix, 0);
       int[] numberIndexes = ArrayTransformations.findIndexInMatrix(matrix, numberToSwap);

        int [][] tempMatrix = new int[matrix.length][];
        for(int i = 0; i < matrix.length; i++)
            tempMatrix[i] = Arrays.copyOf(matrix[i], matrix[i].length);

       tempMatrix[zeroIndexes[0]][zeroIndexes[1]] = numberToSwap;
       tempMatrix[numberIndexes[0]][numberIndexes[1]] = 0;
       return tempMatrix;
    }

    private void printSolution(Node node) {
        // Implement logic to print or return the solution
        // This depends on your specific requirements for displaying the solution

        // Example code to print the solution
        System.out.println("Solution found:");
        while (node != null) {
            printMatrix(node.getValue());
            System.out.println();
            node = node.getParent();
        }
    }

    private void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}

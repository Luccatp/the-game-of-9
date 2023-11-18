package com.ai.trab2.services;

import com.ai.trab2.entities.Node;
import com.ai.trab2.services.interfaces.BreadthFirstInterface;
import com.ai.trab2.utils.ArrayTransformations;
import com.ai.trab2.utils.MazeUtils;

import java.util.*;

public class BreadthFirst implements BreadthFirstInterface {
    private int numberOfNodesCreated = 0;
    private int getNumberOfNodesVisited = 0;


    @Override
    public void solveBreadthFirst(int[][] initialMatrix, int[][] finalMatrix, int x, int y) {
        Node root = new Node(initialMatrix, null);
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        numberOfNodesCreated++;

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            getNumberOfNodesVisited++;

            if (ArrayTransformations.compareMatrixes(currentNode.getValue(), finalMatrix)) {
                // Solution found, print or return the solution
                MazeUtils.printSolution(currentNode, numberOfNodesCreated, getNumberOfNodesVisited);
                return;
            }

            expandNode(currentNode);
            queue.addAll(currentNode.getChildren());
        }

        System.out.println("No solution found.");
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
            boolean isEqualParent = checkIfMatrixIsEqualParent(node, upMatrix);
            if(!isEqualParent) {
                node.addChild(upMatrix);
            }
        }
        if(zeroPosition[0] + 1 < currentValue.length) {
            int down = currentValue[zeroPosition[0] + 1][zeroPosition[1]];
            int[][] downMatrix = swap(currentValue, down);
            boolean isEqualParent = checkIfMatrixIsEqualParent(node, downMatrix);
            if(!isEqualParent) {
                node.addChild(downMatrix);
            }
        }
        if(zeroPosition[1] - 1 >= 0) {
            int left = currentValue[zeroPosition[0]][zeroPosition[1] - 1];
            int[][] leftMatrix = swap(currentValue, left);
            boolean isEqualParent = checkIfMatrixIsEqualParent(node, leftMatrix);
            if(!isEqualParent) {
                node.addChild(leftMatrix);
            }
        }
        if(zeroPosition[1] + 1 < currentValue[0].length) {
            int right = currentValue[zeroPosition[0]][zeroPosition[1] + 1];
            int[][] rightMatrix = swap(currentValue, right);
            boolean isEqualParent = checkIfMatrixIsEqualParent(node, rightMatrix);
            if(!isEqualParent) {
                node.addChild(rightMatrix);
            }
        }
        for (Node childrens : node.getChildren()) {
            this.numberOfNodesCreated++;
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



    private boolean checkIfMatrixIsEqualParent(Node node, int[][] matrix) {
        boolean hasEqualParent = false;
        Node parentNode = node.getParent();
        while (parentNode != null) {
            if(ArrayTransformations.compareMatrixes(matrix, parentNode.getValue())) {
                hasEqualParent = true;
                break;
            }
            parentNode = parentNode.getParent();
        }
        return hasEqualParent;
    }
}

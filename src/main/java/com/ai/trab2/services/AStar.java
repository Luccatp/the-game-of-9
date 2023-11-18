package com.ai.trab2.services;

import com.ai.trab2.entities.Node;
import com.ai.trab2.services.interfaces.AStarInterface;
import com.ai.trab2.utils.ArrayTransformations;
import com.ai.trab2.utils.MazeUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class AStar implements AStarInterface {
    private int numberOfNodesCreated = 0;
    private int getNumberOfNodesVisited = 0;
    private int[][] finalMatrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};

    @Override
    public void solveAStar(int[][] initialMatrix, int[][] finalMatrix, int x, int y) {
        this.finalMatrix = finalMatrix;
        Node root = new Node(initialMatrix, null);
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(this::calculateAStarCost));
        priorityQueue.add(root);

        numberOfNodesCreated++;

        while (!priorityQueue.isEmpty()) {
            Node currentNode = priorityQueue.poll();
            getNumberOfNodesVisited++;

            if (ArrayTransformations.compareMatrixes(currentNode.getValue(), finalMatrix)) {
                // Solution found, print or return the solution
                MazeUtils.printSolution(currentNode, numberOfNodesCreated, getNumberOfNodesVisited);
                return;
            }

            expandNode(currentNode);
            priorityQueue.addAll(currentNode.getChildren());
        }

        System.out.println("No solution found.");
    }

    private void expandNode(Node node) {
        int[][] currentValue = node.getValue();
        int[] zeroPosition = node.getZeroPosition();

        // Example code (you need to adapt it based on the actual game rules)
        if (zeroPosition[0] - 1 >= 0) {
            int up = currentValue[zeroPosition[0] - 1][zeroPosition[1]];
            int[][] upMatrix = swap(currentValue, up);
            boolean isEqualParent = checkIfMatrixIsEqualParent(node, upMatrix);
            if (!isEqualParent) {
                node.addChild(upMatrix);
            }
        }
        if (zeroPosition[0] + 1 < currentValue.length) {
            int down = currentValue[zeroPosition[0] + 1][zeroPosition[1]];
            int[][] downMatrix = swap(currentValue, down);
            boolean isEqualParent = checkIfMatrixIsEqualParent(node, downMatrix);
            if (!isEqualParent) {
                node.addChild(downMatrix);
            }
        }
        if (zeroPosition[1] - 1 >= 0) {
            int left = currentValue[zeroPosition[0]][zeroPosition[1] - 1];
            int[][] leftMatrix = swap(currentValue, left);
            boolean isEqualParent = checkIfMatrixIsEqualParent(node, leftMatrix);
            if (!isEqualParent) {
                node.addChild(leftMatrix);
            }
        }
        if (zeroPosition[1] + 1 < currentValue[0].length) {
            int right = currentValue[zeroPosition[0]][zeroPosition[1] + 1];
            int[][] rightMatrix = swap(currentValue, right);
            boolean isEqualParent = checkIfMatrixIsEqualParent(node, rightMatrix);
            if (!isEqualParent) {
                node.addChild(rightMatrix);
            }
        }

        for (Node children : node.getChildren()) {
            this.numberOfNodesCreated++;
        }
    }

    private int[][] swap(int[][] matrix, int numberToSwap) {
        int[] zeroIndexes = ArrayTransformations.findIndexInMatrix(matrix, 0);
        int[] numberIndexes = ArrayTransformations.findIndexInMatrix(matrix, numberToSwap);

        int[][] tempMatrix = new int[matrix.length][];
        for (int i = 0; i < matrix.length; i++)
            tempMatrix[i] = Arrays.copyOf(matrix[i], matrix[i].length);

        tempMatrix[zeroIndexes[0]][zeroIndexes[1]] = numberToSwap;
        tempMatrix[numberIndexes[0]][numberIndexes[1]] = 0;
        return tempMatrix;
    }

    private boolean checkIfMatrixIsEqualParent(Node node, int[][] matrix) {
        boolean hasEqualParent = false;
        Node parentNode = node.getParent();
        while (parentNode != null) {
            if (ArrayTransformations.compareMatrixes(matrix, parentNode.getValue())) {
                hasEqualParent = true;
                break;
            }
            parentNode = parentNode.getParent();
        }
        return hasEqualParent;
    }

    private int calculateAStarCost(Node node) {
        // G(n): Custo real do caminho do nó inicial até o nó atual
        int gCost = calculateGCost(node);

        // H(n): Custo heurístico do nó atual até o objetivo
        int hCost = calculateHeuristic(node);

        // F(n): Custo total (F(n) = G(n) + H(n))
        return gCost + hCost;
    }

    private int calculateGCost(Node node) {
        // G(n) é o número de movimentos feitos até o nó atual
        int gCost = 0;
        Node currentNode = node;
        while (currentNode.getParent() != null) {
            gCost++;
            currentNode = currentNode.getParent();
        }
        return gCost;
    }

    private int calculateHeuristic(Node node) {
        int[][] currentState = node.getValue();
        int[][] goalState = this.finalMatrix;

        int heuristic = 0;

        for (int i = 0; i < currentState.length; i++) {
            for (int j = 0; j < currentState[i].length; j++) {
                int value = currentState[i][j];
                if (value != 0) {
                    int[] goalPosition = ArrayTransformations.findIndexInMatrix(goalState, value);
                    heuristic += Math.abs(i - goalPosition[0]) + Math.abs(j - goalPosition[1]);
                }
            }
        }

        return heuristic;
    }
}

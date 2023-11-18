package com.ai.trab2.utils;

import com.ai.trab2.entities.Node;

public class MazeUtils {
    public static void printSolution(Node node, int numberOfNodesCreated, int getNumberOfNodesVisited) {
        int treeDepth = 0;

        System.out.println("Solution found:");
        while (node != null) {
            treeDepth++;
            ArrayTransformations.printMatrix(node.getValue());
            System.out.println();
            node = node.getParent();
        }
        System.out.println("Number of nodes created: " + numberOfNodesCreated);
        System.out.println("Number of nodes visited: " + getNumberOfNodesVisited);
        System.out.println("Tree depth: " + treeDepth);
    }
}

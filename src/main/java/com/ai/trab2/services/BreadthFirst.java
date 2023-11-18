package com.ai.trab2.services;

import com.ai.trab2.entities.Table;
import com.ai.trab2.entities.Node;
import com.ai.trab2.services.interfaces.BreadthFirstInterface;
import com.ai.trab2.utils.ArrayTransformations;

import java.util.*;

public class BreadthFirst implements BreadthFirstInterface {
    @Override
    public void solveBreadthFirst(int[][] initialMatrix, int[][] finalMatrix, int x, int y) {
        Table initialTable = new Table(initialMatrix);
        Table finalTable = new Table(finalMatrix);

        Node solutionTree = breadthFirstSearch(initialTable, finalTable);
        ArrayTransformations.printMatrix(solutionTree.getTable().getMatrix());
    }

    private static void generateAndEnqueueChildren(Queue<Node> queue, Node parent, Table finalTable) {
        List<Table> possibleMoves = Arrays.asList(
                new Table(parent.getTable().swapUp()),
                new Table(parent.getTable().swapDown()),
                new Table(parent.getTable().swapRight()),
                new Table(parent.getTable().swapLeft())
        );

        for (Table move : possibleMoves) {
            Node childNode = parent.addChild(move);
            queue.add(childNode);
        }
    }

    private Node breadthFirstSearch(Table initialTable, Table finalTable) {
        Set<Table> visited = new HashSet<>();
        Node root = new Node(initialTable);
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            Table currentTable = currentNode.getTable();

            ArrayTransformations.printMatrix(currentTable.getMatrix());
            System.out.println(" ");

            if (currentNode.getTable().compareTo(finalTable) == 0) {
                // Goal state reached, return the solution tree
                return currentNode;
            }

            if (!visited.contains(currentTable)) {
                visited.add(currentTable);

                // Generate child nodes and add them to the queue
                generateAndEnqueueChildren(queue, currentNode, finalTable);
            }
        }

        System.out.println("No solution found");
        return null; // No solution found
    }
}

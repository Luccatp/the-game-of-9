package com.ai.trab2.services;

import com.ai.trab2.entities.Table;
import com.ai.trab2.entities.Tree;
import com.ai.trab2.services.interfaces.BreadthFirstInterface;
import com.ai.trab2.utils.ArrayTransformations;

import java.util.*;

public class BreadthFirst implements BreadthFirstInterface {
    private LinkedList<Tree> linkedList = new LinkedList<Tree>();
    private boolean isFinalTable = false;
    @Override
    public void solveBreadthFirst(int[][] initialMatrix, int[][] finalMatrix, int x, int y) {
        Table initialTable = new Table(initialMatrix);
        Table finalTable = new Table(finalMatrix);

        Tree solutionTree = breadthFirstSearch(initialTable, finalTable);
        ArrayTransformations.printMatrix(solutionTree.getTable().getMatrix());
    }

    private static void generateAndEnqueueChildren(Queue<Tree> queue, Tree parent, Table finalTable) {
        List<Table> possibleMoves = Arrays.asList(
                new Table(parent.getTable().swapUp()),
                new Table(parent.getTable().swapDown()),
                new Table(parent.getTable().swapRight()),
                new Table(parent.getTable().swapLeft())
        );

        for (Table move : possibleMoves) {
            Tree childNode = parent.addChild(move);
            queue.add(childNode);
        }
    }

    private Tree breadthFirstSearch(Table initialTable, Table finalTable) {
        Set<Table> visited = new HashSet<>();
        Tree root = new Tree(initialTable);
        Queue<Tree> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Tree currentNode = queue.poll();
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

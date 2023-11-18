package com.ai.trab2.entities;

import com.ai.trab2.utils.ArrayTransformations;
import lombok.Getter;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Getter
public class Node {
    private final int[][] value;
    private final Node parent;
    private final int[] zeroPosition;
    private List<Node> children;

    public Node(int[][] value, Node parent) {
        this.value = value;
        this.parent = parent;
        this.children = new LinkedList<>();

        zeroPosition = ArrayTransformations.findIndexInMatrix(value, 0);
    }

    public Node addChild(int[][] value) {
        Node newChild = new Node(value, this);
        children.add(newChild);
        return newChild;
    }
}

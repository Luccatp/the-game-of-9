package com.ai.trab2.entities;

import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@Getter
public class Node {
    private Table table;
    private List<Node> children;

    public Node(Table table) {
        this.table = table;
        this.children = new LinkedList<>();
    }

    public Node addChild(Table table) {
        Node newChild = new Node(table);
        children.add(newChild);
        return newChild;
    }
}

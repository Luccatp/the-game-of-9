package com.ai.trab2.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@Getter
public class Tree {
    private Table table;
    private List<Tree> children;

    public Tree(Table table) {
        this.table = table;
        this.children = new LinkedList<>();
    }

    public Tree addChild(Table table) {
        Tree newChild = new Tree(table);
        children.add(newChild);
        return newChild;
    }
}

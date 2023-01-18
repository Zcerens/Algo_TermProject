package com.java.lib.tree.element;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeTest {

    @Test
    void add() {
        Tree tree = new Tree();
        tree.add("hello");
        tree.add("world");
        tree.add("world");

        assertEquals(2, tree.find("world").getVal());
        assertEquals(1, tree.find("hello").getVal());
    }

    @Test
    void find() {
        Tree tree = new Tree();
        tree.add("hello");
        tree.add("world");
        tree.add("world");

        assertEquals(2, tree.find("world").getVal());
        assertEquals(1, tree.find("hello").getVal());
        // check null is returned when word is not found
        assertEquals(null, tree.find("hell"));
    }

    @Test
    void wordCount() {
        Tree tree = new Tree();
        tree.add("hello");
        tree.add("world");
        tree.add("world");
        // check duplicate word is not counted
        assertEquals(2, tree.wordCount());
    }

    @Test
    void nodeCount() {
        Tree tree = new Tree();
        tree.add("hello");
        tree.add("world");
        tree.add("world");
        tree.add("world");
        assertEquals(11, tree.nodeCount());
    }

    @Test
    void testToString() {
        Tree tree = new Tree();
        tree.add("hello");
        tree.add("world");
        tree.add("world");

        // check toString is works
        assertEquals("hello\nworld", tree.toString());

        Tree emptyTree = new Tree();
        // check empty tree is empty
        assertEquals("", emptyTree.toString());
    }

    @Test
    void testHashCode() {
        Tree tree = new Tree();
        tree.add("hello");
        tree.add("world");
        tree.add("world");
        // check hash code
        assertEquals(325799926, tree.hashCode());
    }

    @Test
    void testEquals() {
        Tree tree = new Tree();
        tree.add("hello");
        tree.add("world");
        tree.add("world");

        Tree tree2 = new Tree();
        tree2.add("hello");
        tree2.add("world");
        tree2.add("world");

        // check some different tree
        assertEquals(tree, tree2);

        Tree tree3 = new Tree();
        tree3.add("hello");
        Tree tree4 = new Tree();
        tree4.add("hello");

        // check some different tree
        assertEquals(tree3, tree4);

        // check different tree and their not equal
        assertNotEquals(tree3, tree);
    }
}
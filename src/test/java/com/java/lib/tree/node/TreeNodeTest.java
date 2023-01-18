package com.java.lib.tree.node;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeNodeTest {

    @Test
    void getVal() {
        TreeNode node = new TreeNode();
        // check default is 0
        assertEquals(0, node.getVal());

        node.incrementVal();
        // check value is changed
        assertEquals(1, node.getVal());

        node.incrementVal();
        // check value is changed again
        assertEquals(2, node.getVal());
    }

    @Test
    void incrementVal() {
        TreeNode node = new TreeNode();
        // check default is 0
        assertEquals(0, node.getVal());

        node.incrementVal();
        // check increment is works
        assertEquals(1, node.getVal());

        node.incrementVal();
        // check increment is works again
        assertEquals(2, node.getVal());
    }

    @Test
    void setIsEnd() {
        TreeNode node = new TreeNode();
        assertFalse(node.getIsEnd());

        node.setIsEnd(true);
        assertTrue(node.getIsEnd());

        node.setIsEnd(false);
        assertFalse(node.getIsEnd());
    }

    @Test
    void getIsEnd() {
        TreeNode node = new TreeNode();
        // check default is false
        assertFalse(node.getIsEnd());

        node.setIsEnd(true);
        assertTrue(node.getIsEnd());

        node.setIsEnd(false);
        // check node is changed
        assertFalse(node.getIsEnd());
    }

    @Test
    void getChild() {
        TreeNode node = new TreeNode();
        // default node has no child
        assertEquals(26, node.getChild().length);
    }
}
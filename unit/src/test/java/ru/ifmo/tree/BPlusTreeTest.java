package ru.ifmo.tree;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class BPlusTreeTest {

    private BPlusTree tree;


    @Before
    public void setup() {
        tree = new BPlusTree(7);
    }

    @Test
    public void emptyTreeCheckRootClass() {
        var expected = BPlusTree.LeafNode.class;
        var actual = tree.getRoot().getClass();
        assertEquals(expected, actual);
    }

    @Test
    public void insertSixCheckNoSplit() {
        for (int i = 0; i < 6; i++) {
            tree.insert(6);
        }
        var expected = BPlusTree.LeafNode.class;
        var actual = tree.getRoot().getClass();
        assertEquals(expected, actual);
    }

    @Test
    public void insertSeven_checkRootIsNode_checkLeafNodeSplit() {
        for (int i = 0; i < 7; i++) {
            tree.insert(i);
        }

        var root = tree.getRoot();
        assertEquals(root.getClass(), BPlusTree.InnerNode.class);

        var expectedValue = Integer.valueOf(3);
        var actualValue = root.getEntries().first().getValue();
        assertEquals(expectedValue, actualValue);

        BPlusTree.InnerNode rootNode = (BPlusTree.InnerNode) root;
        var link = (BPlusTree.InnerNode.NodeLink) rootNode.getEntries().first();
        var containsSmaller
                = link.getLeft()
                .getEntries()
                .stream()
                .map(BPlusTree.Node.Entry::getValue)
                .collect(Collectors.toList())
                .containsAll(List.of(0, 1, 2));
        assertTrue(containsSmaller);

        var containsGreater
                = link.getRight()
                .getEntries()
                .stream()
                .map(BPlusTree.Node.Entry::getValue)
                .collect(Collectors.toList())
                .containsAll(List.of(3, 4, 5, 6));
        assertTrue(containsGreater);
    }


    @Test
    public void insert25_checkInnerNodesSplit_checkLeavesListIntegrity() {
        for (int i = 1; i < 26; i++) {
            tree.insert(i);
        }

        var rootLink = (BPlusTree.InnerNode.NodeLink) tree.getRoot().getEntries().first();
        var rootValue = rootLink.getValue();
        assertEquals(Integer.valueOf(13), rootValue);

        var leftContains = rootLink.getLeft().getEntries()
                .stream()
                .map(BPlusTree.Node.Entry::getValue)
                .collect(Collectors.toList())
                .containsAll(List.of(4, 7, 10));
        assertTrue(leftContains);

        var rightContains = rootLink.getRight().getEntries()
                .stream()
                .map(BPlusTree.Node.Entry::getValue)
                .collect(Collectors.toList())
                .containsAll(List.of(16, 19, 22));
        assertTrue(rightContains);


        var leaf = (BPlusTree.LeafNode) ((BPlusTree.InnerNode.NodeLink) rootLink.getLeft().getEntries().first()).getLeft();
        for (int i = 0; i < 21; i += 3) {
            assertTrue(leaf.getEntries().stream()
                    .map(BPlusTree.Node.Entry::getValue)
                    .collect(Collectors.toList())
                    .containsAll(List.of(i + 1, i + 2, i + 3)));
            leaf = leaf.getNext();
        }

        var lastValueEquals25 = leaf.getEntries().last().getValue().equals(25);
        assertTrue(lastValueEquals25);

    }


}

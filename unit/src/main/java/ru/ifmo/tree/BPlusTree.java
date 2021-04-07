package ru.ifmo.tree;

import lombok.*;

import java.util.NavigableSet;
import java.util.TreeSet;

@RequiredArgsConstructor
public class BPlusTree {

    @Getter
    private Node<?> root = new LeafNode(this, new TreeSet<>());
    private final int NODE_CAPACITY;

    public void insert(Integer integer) {
        root.insert(integer);
    }

    private void split(Node<?> node) {
        throw new RuntimeException("Cannot split abstract node. CRAP!");
    }

    private void split(InnerNode node) {
        var rightNode = node.splitRight();
        if ((node == root) == node.hasParent()) {
            throw new RuntimeException("Invalid tree state.");
        }

        var linkInt = rightNode.popFirst();
        InnerNode.NodeLink link;

        if (node.hasParent()) {
            link = node.getParent().linkOf(linkInt);
        } else {
            // node is root!
            var rootNode = new InnerNode(this, new TreeSet<>());
            root = rootNode;
            node.setParent(rootNode);

            link = node.linkOf(linkInt);
        }
        link.setLeft(node);
        link.setRight(rightNode);
        rightNode.setParent(node.getParent());
        node.getParent().add(link);
    }

    private void split(LeafNode leaf) {
        var rightLeaf = leaf.splitRight();
        if (leaf.getNext() != null) {
            rightLeaf.setNext(leaf.getNext());
        }
        leaf.setNext(rightLeaf);

        if ((leaf == root) == leaf.hasParent()) {
            throw new RuntimeException("Invalid tree state.");
        }

        var linkInt = rightLeaf.first();
        InnerNode.NodeLink link;

        if (leaf.hasParent()) {
            link = leaf.getParent().linkOf(linkInt);
        } else {
            // leaf is root!
            var node = new InnerNode(this, new TreeSet<>());
            root = node;
            leaf.setParent(node);
            link = node.linkOf(linkInt);
        }
        rightLeaf.setParent(leaf.getParent());
        link.setLeft(leaf);
        link.setRight(rightLeaf);
        leaf.getParent().add(link);
    }

    static abstract class Node<E extends Node.Entry> {
        @Getter
        private final BPlusTree tree;
        @Getter
        @Setter
        private InnerNode parent;
        @Getter
        private final NavigableSet<E> entries;

        public Node(BPlusTree tree, NavigableSet<E> entries) {
            this.tree = tree;
            this.entries = entries;
        }

        public Node(BPlusTree tree) {
            this(tree, new TreeSet<>());
        }


        void split() {
            tree.split(this);
        }

        boolean hasParent() {
            return parent != null;
        }

        Node<E> splitRight() {
            var entrySet = new TreeSet<E>();
            double impreciseAmount = ((double) /* TODO NODE_CAPACITY*/ 7)  / 2.0;
            int amount = (int) Math.ceil(impreciseAmount);
            for (int i = 0; i < amount; i++) {
                entrySet.add(entries.pollLast());
            }
            return of(tree, entrySet);
        }

        Integer popFirst() {
            return entries.pollFirst().getValue();
        }

        Integer first() {
            return entries.first().getValue();
        }

        void add(E e) {
            entries.add(e);
            if (entries.size() == 7 /* TODO NODE_CAPACITY*/) {
                split();
            }
        }

        abstract Node<E> of(BPlusTree tree, NavigableSet<E> set);

        abstract void insert(Integer integer);

        @Data
        abstract static class Entry implements Comparable<Entry> {
            private final Integer value;

            @Override
            public int compareTo(Entry o) {
                return value.compareTo(o.value);
            }
        }

    }


    public static class InnerNode extends Node<InnerNode.NodeLink> {

        public InnerNode(BPlusTree tree, NavigableSet<NodeLink> entries) {
            super(tree, entries);
        }

        public InnerNode(BPlusTree tree) {
            super(tree, new TreeSet<>());
        }

        @Override
        void split() {
            getTree().split(this);
        }

        @Override
        InnerNode of(BPlusTree tree, NavigableSet<NodeLink> set) {
            return new InnerNode(tree, set);
        }


        @Override
        void insert(Integer integer) {
            var link = linkOf(integer);
            var lesserNode = getEntries().floor(link);
            if (lesserNode != null) {
                lesserNode.getRight().insert(integer);
            } else {
                getEntries().first().getLeft().insert(integer);
            }
        }

        @Override
        InnerNode splitRight() {
            return (InnerNode) super.splitRight();
        }

        NodeLink linkOf(Integer integer) {
            return new NodeLink(integer);
        }

        @Getter
        @Setter
        @EqualsAndHashCode(callSuper = true)
        static
        class NodeLink extends Entry {
            private Node<?> left;
            private Node<?> right;

            public NodeLink(Integer integer) {
                super(integer);
            }
        }
    }

    public static class LeafNode extends Node<LeafNode.Bucket> {
        @Setter
        @Getter
        LeafNode next;

        private LeafNode(BPlusTree tree, NavigableSet<Bucket> entries) {
            super(tree, entries);
        }

        @Override
        LeafNode splitRight() {
            return (LeafNode) super.splitRight();
        }

        @Override
        LeafNode of(BPlusTree tree, NavigableSet<Bucket> set) {
            return new LeafNode(tree, set);
        }

        @Override
        void insert(Integer integer) {
            var bucket = bucketOf(integer);
            add(bucket);
        }

        @Override
        void split() {
            getTree().split(this);
        }

        Bucket bucketOf(Integer integer) {
            return new Bucket(integer);
        }

        static class Bucket extends Entry {
            Bucket(Integer integer) {
                super(integer);
            }
        }

    }

}

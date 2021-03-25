package ru.ifmo.tree.first;

import lombok.*;

import java.util.*;

public class BPTree<K extends Comparable<K>, V> implements ExposedTree<K, V> {
    private Node root;
    private final int CAPACITY;

    BPTree(int capacity) {
        this.CAPACITY = capacity;
        root = new Node(new TreeSet<>(), null);
    }

    public static <K extends Comparable<K>, V> Tree<K, V> of(int capacity) {
        return new BPTree<>(capacity);
    }

    @Override
    public void expose() {

    }

    @Override
    public void insert(K key, V value) {

    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public V search(K key) {
        return null;
    }

    @RequiredArgsConstructor
    class Node {
        @NonNull private final NavigableSet<NodeEntry> entries;
        private final Node parent;

        boolean isRoot() {
            return parent == null;
        }

        boolean isLeaf() {
            return true; //TODO
        }

        void insert(NodeEntry entry) {
            entries.add(entry);
            if (entries.size() == CAPACITY) {
                this.split();
            }
        }

        private void split() {
            NodeEntry pivot = entries.first();
            int pivotIndex = CAPACITY / 2 + 1;

            var iterator = entries.iterator();
            for (int i = 0; i < pivotIndex; i++) {
                pivot = iterator.next();
            }

            var left = entries.headSet(pivot, false);
            var right = entries.tailSet(pivot, true);
            entries.clear();

            var leftNode = new Node(left, this);
            var rightNode = new Node(right, this);
            pivot = new EntryWrapper(pivot.getKey(), leftNode, rightNode);
            entries.add(pivot);
        }

        void remove(NodeEntry entry) {
            entries.remove(entry);
        }
    }

    @Data
    private abstract class NodeEntry implements Comparable<NodeEntry> {
        @NonNull
        final private K key;

        @Override
        public int compareTo(NodeEntry o) {
            return key.compareTo(o.getKey());
        }
    }

    @Getter
    @Setter
    @EqualsAndHashCode(callSuper = true)
    class EntryWrapper extends NodeEntry {
        @NonNull
        private Node left;
        @NonNull
        private Node right;

        private EntryWrapper(@NonNull K key) {
            super(key);
        }

        EntryWrapper(@NonNull K key, @NonNull Node left, @NonNull Node right) {
            this(key);
            this.left = left;
            this.right = right;
        }
    }

    @EqualsAndHashCode(callSuper = true)
    class Bucket extends NodeEntry {
        @NonNull
        final private V value;
    }

}

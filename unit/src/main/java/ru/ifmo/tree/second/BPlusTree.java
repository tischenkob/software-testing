package ru.ifmo.tree.second;

import lombok.*;
import ru.ifmo.tree.first.ExposedTree;

import java.util.NavigableSet;
import java.util.TreeSet;

final public class BPlusTree<K extends Comparable<K>, V> implements ExposedTree<K, V> {
    private Node root;
    private final int MAX_NODE_CAPACITY;
    private final int SPLIT_PIVOT_INDEX;

    @Data
    abstract class Node {
        private InnerNode parent;
        private NavigableSet<KeyHolder> holders;

        public boolean hasParent() {
            return parent != null;
        }

        protected void add(KeyHolder holder) throws IllegalArgumentException {
            this.holders.add(holder);
            if (holders.size() == MAX_NODE_CAPACITY) {
                split();
            }
        }

        protected void remove(KeyHolder holder) {
            this.holders.remove(holder);
            if (holders.size() == MAX_NODE_CAPACITY / 2) {
                fold();
            }
        }

        protected NavigableSet<KeyHolder> splitRight() {
            NavigableSet<KeyHolder> rightSet = new TreeSet<>();
            for (int i = 0; i < SPLIT_PIVOT_INDEX; i++) {
                rightSet.add(holders.pollLast());
            }
            return rightSet;
        }

        protected KeyHolder getSmallest() {
            return this.holders.first();
        }

        abstract protected void split();

        abstract protected void fold();

        @Getter
        @RequiredArgsConstructor
        abstract class KeyHolder implements Comparable<KeyHolder> {
            @NonNull
            private final K key;

            @Override
            public int compareTo(KeyHolder o) {
                return key.compareTo(o.getKey());
            }
        }

    }

    @Data
    final class InnerNode extends Node {

        InnerNode() {

        }

        @Override
        protected void add(KeyHolder holder) throws IllegalArgumentException {
            if (holder.getClass() != LinkedKey.class) {
                throw new IllegalArgumentException("InnerNode can only contain LinkedKey's");
            }
            super.add(holder);
        }

        @Override
        protected void split() {

        }

        @Override
        protected void fold() {

        }

        @Data
        final class LinkedKey extends KeyHolder {
            private Node left;
            private Node right;
        }
    }

    @Getter @Setter
    final class LeafNode extends Node {
        private LeafNode next;

        @Override
        protected void add(KeyHolder holder) throws IllegalArgumentException {
            if (holder.getClass() != Bucket.class) {
                throw new IllegalArgumentException("LeafNode only accepts instances of Bucket");
            }
            super.add(holder);
        }

        @Override
        protected void split() {

            // Create sibling
            var newLeaf = new LeafNode();
            newLeaf.setNext(this.getNext());
            this.next = newLeaf;
            var rightSet = splitRight();
            newLeaf.setHolders(rightSet);

            // Check parent
            if (!hasParent()) {
                var parent = new InnerNode();
                setParent(parent);
            }


            getParent().add();


        }

        @Override
        protected void fold() {

        }

        @Getter
        final class Bucket extends KeyHolder {
            @NonNull
            private final V value;

            public Bucket(K key, V value) {
                super(key);
                this.value = value;
            }
        }
    }

}

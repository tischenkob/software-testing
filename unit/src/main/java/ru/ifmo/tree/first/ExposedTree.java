package ru.ifmo.tree.first;

interface ExposedTree<K extends Comparable<K>, V> extends Tree<K, V> {
    void expose();
}

package ru.ifmo.tree.first;

public interface Tree<K extends Comparable<K>, V> {
    void insert(K key, V value);
    V remove(K key);
    V search(K key);
}

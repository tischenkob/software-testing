package ru.ifmo.tree;

import java.util.NavigableSet;

public class Node<K extends Comparable<K>> {
    NavigableSet<Key> keys;

    void add(K key) {
        keys.add(new Key(key));

        // TODO тут можно и в начало ведь вставить
        var left = keys.ceiling(key).left;

    }

    private class Key {
        K key;
        Node<K> left;
        Node<K> right;
    }
}

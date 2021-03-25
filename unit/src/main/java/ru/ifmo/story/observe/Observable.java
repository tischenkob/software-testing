package ru.ifmo.story.observe;

public interface Observable<T> {
    void emit(T signal, Object... args);
    void addListener(Observer<T> listener);
    void removeListener(Observer<T> listener);
}


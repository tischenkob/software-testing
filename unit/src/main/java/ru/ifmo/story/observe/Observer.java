package ru.ifmo.story.observe;

public interface Observer<T> {
    void reactTo(T signal, Object... args);

    default void observe(Iterable<? extends Observable<T>> observable) {
        observable.forEach(o -> o.addListener(this));
    };

    default void forget(Iterable<? extends Observable<T>> observable) {
        observable.forEach(o -> o.removeListener(this));
    };
}

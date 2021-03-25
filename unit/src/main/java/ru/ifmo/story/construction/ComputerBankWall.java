package ru.ifmo.story.construction;

import ru.ifmo.story.observe.Observer;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;


public class ComputerBankWall implements Wall {
    Set<Observer<Wall.Signal>> listeners = new HashSet<>();

    @Override
    public Streamlet melt() {
        emit(Signal.MELTED, this);
        return Streamlet.of(Liquid.METAL);
    }

    @Override
    public void emit(Signal signal, Object... args) {
        listeners.forEach(l -> l.reactTo(signal, args));
    }

    @Override
    public void addListener(Observer<Signal> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(Observer<Signal> listener) {
        listeners.remove(listener);
    }
}



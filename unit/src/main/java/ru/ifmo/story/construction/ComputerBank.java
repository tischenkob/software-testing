package ru.ifmo.story.construction;

import ru.ifmo.story.observe.Observable;

import java.util.HashSet;
import java.util.Set;

public class ComputerBank implements Building {

    private final Set<Wall> walls = new HashSet<>();

    public ComputerBank(Wall a, Wall b, Wall c, Wall d) {
        walls.addAll(Set.of(a, b, c, d));
        if (walls.size() != 4) throw new IllegalStateException("Building must have 4 different walls");
        observe(walls);
    }

    @Override
    public boolean hasWall(Wall wall) {
        return walls.contains(wall);
    }

    @Override
    public void collapse() {
        forget(walls);
        walls.clear();
    }

    @Override
    public void reactTo(Wall.Signal signal, Object... args) {
        switch (signal) {
            case MELTED:
                collapse();
                break;
        }
    }
}

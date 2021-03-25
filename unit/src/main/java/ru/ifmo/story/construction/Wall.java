package ru.ifmo.story.construction;

import ru.ifmo.story.observe.Observable;

interface Wall extends Observable<Wall.Signal> {
    enum Signal {
        MELTED
    }

    Streamlet melt();
}

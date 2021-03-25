package ru.ifmo.story.construction;


import ru.ifmo.story.observe.Observer;

interface Building extends Observer<Wall.Signal> {

    boolean hasWall(Wall wall);

    void collapse();

}



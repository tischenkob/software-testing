package ru.ifmo.story.env;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Event {
    enum State {
        NOT_STARTED, IN_PROGRESS, PAUSED, FINISHED
    }

    State getState();

    State transitionTo(State state);
}


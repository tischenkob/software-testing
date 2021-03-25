package ru.ifmo.story.env;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class Bombardment implements Event {
    private State state = State.NOT_STARTED;

    private static final Map<State, Set<State>> transitionsMap = new HashMap<>();

    static {
        transitionsMap.put(State.NOT_STARTED, Set.of(State.IN_PROGRESS));
        transitionsMap.put(State.IN_PROGRESS, Set.of(State.FINISHED, State.PAUSED));
        transitionsMap.put(State.PAUSED, Set.of(State.IN_PROGRESS, State.FINISHED));
    }

    @Override
    public State transitionTo(State state) {
        Set<State> transitions = transitionsMap.get(this.state);
        if (transitions == null || !transitions.contains(state)) {
            return this.state;
        }
        this.state = state;
        return this.state;
    }

    @Override
    public State getState() {
        return this.state;
    }

}

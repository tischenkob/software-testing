package ru.ifmo.story.env;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class BombardmentTest {
    Event bombardment;

    @BeforeEach
    public void setup() {
        bombardment = new Bombardment();
    }

    @Test
    public void canTransitionToInProgressFromNotStarted() {
        assertEquals(bombardment.getState(), Event.State.NOT_STARTED);

        Event.State returned = bombardment.transitionTo(Event.State.IN_PROGRESS);
        Event.State actual = bombardment.getState();
        Event.State expected = Event.State.IN_PROGRESS;

        assertEquals(returned, actual);
        assertEquals(actual, expected);
    }

    @Test
    public void cannotTransitionToFinishedFromNotStarted_ToNotStartedFromInProgress() {
        assertEquals(bombardment.getState(), Event.State.NOT_STARTED);

        bombardment.transitionTo(Event.State.FINISHED);
        assertNotEquals(bombardment.getState(), Event.State.FINISHED);

        bombardment.transitionTo(Event.State.IN_PROGRESS);
        bombardment.transitionTo(Event.State.NOT_STARTED);

        assertNotEquals(bombardment.getState(), Event.State.NOT_STARTED);

    }

}

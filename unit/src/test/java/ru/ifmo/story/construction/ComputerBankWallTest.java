package ru.ifmo.story.construction;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.concurrent.atomic.AtomicBoolean;


public class ComputerBankWallTest {
    Wall wall = new ComputerBankWall();

    @Before
    public void setup(){
        wall = new ComputerBankWall();
    }

    @Test
    public void meltReturnsMetalStreamlet_signalMeltedIsEmitted() {
        AtomicBoolean signalEmitted = new AtomicBoolean(false);

        wall.addListener((signal, args) -> {
            if (signal.equals(Wall.Signal.MELTED)) signalEmitted.set(true);
        });

        Streamlet streamlet = wall.melt();

        assertEquals(streamlet, Streamlet.of(Liquid.METAL));
        assertTrue(signalEmitted.get());
    }
}
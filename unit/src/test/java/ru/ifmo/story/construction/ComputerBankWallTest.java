package ru.ifmo.story.construction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.atomic.AtomicBoolean;


public class ComputerBankWallTest {
    Wall wall = new ComputerBankWall();

    @BeforeEach
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

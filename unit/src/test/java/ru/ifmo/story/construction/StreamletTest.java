package ru.ifmo.story.construction;

import org.junit.Test;

import static org.junit.Assert.*;

public class StreamletTest {

    @Test
    public void correctToString() {
        String actual = Streamlet.of(Liquid.METAL).toString();
        String expected = "Streamlet of METAL";
        assertEquals(actual, expected);
    }
}
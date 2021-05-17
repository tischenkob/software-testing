package ru.ifmo.story.construction;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class StreamletTest {

    @Test
    public void correctToString() {
        String actual = Streamlet.of(Liquid.METAL).toString();
        String expected = "Streamlet of METAL";
        assertEquals(actual, expected);
    }
}

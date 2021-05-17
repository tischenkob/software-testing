package ru.ifmo.math.trigonometry;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CosTest {

    private Cos cos;

    @Before
    public void setUp() throws Exception {
        cos = new Cos(new Sin(1));
    }

    @Test
    public void of() {
    }
}

package ru.ifmo.math.trigonometry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SinTest {

    private final double ACCURACY = 0.01;
    private SineFunction sin;

    @BeforeEach
    public void setUp() throws Exception {
        sin = new Sin(ACCURACY);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/Sine.csv")
    public void of(Double expected, Double x) {
        Double actual = sin.of(x);
        assertEquals(expected, actual, ACCURACY);
    }
}

package ru.ifmo.math.logarithmic;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.ifmo.math.trigonometry.Sin;
import ru.ifmo.math.trigonometry.SineFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NaturalLogTest {

    private final double ACCURACY = 0.001;
    private Logarithmic ln;

    @BeforeEach
    public void setUp() throws Exception {
        ln = new NaturalLog(ACCURACY);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/NaturalLog.csv")
    public void of(double expected, double x) {
        double actual = ln.of(x);
        assertEquals(expected, actual, ACCURACY);
    }
}

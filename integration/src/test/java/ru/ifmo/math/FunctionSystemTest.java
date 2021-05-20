package ru.ifmo.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FunctionSystemTest {

    @Test
    public void of() {
        Computable negativeArgsFunction = x -> -1;
        Computable positiveArgsFunction = x -> 2;
        FunctionSystem system = new FunctionSystem(negativeArgsFunction, positiveArgsFunction);
        var actualPositive = system.of(0.00000001);
        var actualNegative = system.of(-0.00000001);

        var expected = 2;
        assertEquals(expected, actualPositive);

        expected = -1;
        assertEquals(expected, actualNegative);
    }
}

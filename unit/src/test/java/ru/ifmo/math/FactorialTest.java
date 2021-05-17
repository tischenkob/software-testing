package ru.ifmo.math;


import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FactorialTest {

    private Map<Integer, Long> expectedMap = new HashMap<>();

    {
        expectedMap.put(0, 1L);
        expectedMap.put(3, 6L);
        expectedMap.put(4, 24L);
        expectedMap.put(5, 120L);
        expectedMap.put(15, 1307674368000L);
        expectedMap.put(16, 20922789888000L);
        expectedMap.put(17, 355687428096000L);
    }

    @Test
    public void of() {
        for (var entry: expectedMap.entrySet()) {
            var key = entry.getKey();
            var expectedValue = entry.getValue();
            var actualValue = Factorial.of(key);

            assertEquals(expectedValue, actualValue);
        }
    }
}

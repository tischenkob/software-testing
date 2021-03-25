package ru.ifmo.algo;

import java.util.ArrayList;
import java.util.List;

public class Factorial {
    private static final List<Long> FACTORIALS_CACHE = new ArrayList<>();

    private static final int INITIAL_CACHE_SIZE = 7;
    private static final long ZERO_FACTORIAL = 1L;
    private static int lastCalculatedValueIndex = 0;

    static {
        FACTORIALS_CACHE.add(0, ZERO_FACTORIAL);
        catchUpWith(INITIAL_CACHE_SIZE);
    }

    private Factorial() {
    }

    public static Long of(int value) {
        if (value < 0) throw new IllegalArgumentException("Factorial value cannot be less than 0.");
        long result = 1L;
        try {
            result = FACTORIALS_CACHE.get(value);
            if (result == 0L) throw new IndexOutOfBoundsException();
        } catch (IndexOutOfBoundsException e) {
            result = catchUpWith(value);
        }
        return result;
    }

    private static long catchUpWith(int index) {
        while (lastCalculatedValueIndex <= index) {
            long previousValue = FACTORIALS_CACHE.get(lastCalculatedValueIndex);
            int currentIndex = lastCalculatedValueIndex + 1;

            long currentValue = previousValue * currentIndex;
            FACTORIALS_CACHE.add(currentIndex, currentValue);
            lastCalculatedValueIndex = currentIndex;
        }
        return FACTORIALS_CACHE.get(index);
    }
}

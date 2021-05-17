package ru.ifmo.math.logarithmic;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Log implements Logarithmic {
    private final Logarithmic core;
    private final int base;

    @Override
    public double of(double x) {
        return core.of(x) / core.of(base);
    }
}

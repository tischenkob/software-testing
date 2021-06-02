package ru.ifmo.math.logarithmic;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Log implements Logarithmic {
    private final Logarithmic log;
    private final int base;

    @Override
    public double of(double x) {
        return log.of(x) / log.of(base);
    }
}

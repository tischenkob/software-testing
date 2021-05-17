package ru.ifmo.math;

import lombok.RequiredArgsConstructor;

public abstract class PreciseFunction implements Computable {
    protected final double PRECISION;

    public PreciseFunction(double precision) {
        if (precision <= 0) throw new IllegalArgumentException("Precision must be > 0");
        PRECISION = precision;
    }
}

package ru.ifmo.math;

public abstract class AccurateFunction implements Computable {
    protected final double ACCURACY;

    public AccurateFunction(double precision) {
        if (precision <= 0) throw new IllegalArgumentException("Precision must be > 0");
        ACCURACY = precision;
    }
}

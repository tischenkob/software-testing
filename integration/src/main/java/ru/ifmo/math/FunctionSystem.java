package ru.ifmo.math;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FunctionSystem implements Computable {
    private final Computable negativeArgsFunction, positiveArgsFunction;

    @Override
    public double of(double x) {
        return (x > 0) ? positiveArgsFunction.of(x) : negativeArgsFunction.of(x);
    }
}

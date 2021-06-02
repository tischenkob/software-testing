package ru.ifmo.math;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FunctionSystem implements Computable {
    private final Computable negativeArgsFunction, positiveArgsFunction;

    @Override
    public double of(double x) {
        if (x == 0) return 0;
        if (x > 0) return positiveArgsFunction.of(x);
        return negativeArgsFunction.of(x);
    }
}

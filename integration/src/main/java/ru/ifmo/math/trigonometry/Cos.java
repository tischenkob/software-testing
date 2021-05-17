package ru.ifmo.math.trigonometry;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Cos implements CosineFunction {

    private final SineFunction sin;

    @Override
    public double of(double x) {
        return Math.sqrt(1 - Math.pow(sin.of(x), 2));
    }
}

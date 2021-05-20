package ru.ifmo.math.trigonometry;

import lombok.RequiredArgsConstructor;

import static java.lang.Math.PI;

@RequiredArgsConstructor
public class Cos implements CosineFunction {

    private final SineFunction sin;

    @Override
    public double of(double x) {
        double sign = (x > PI / 2) ? -1 : 1;
        double square = Math.pow(sin.of(x), 2);
        if (Math.abs(square - 1) < 0.0001) square = 1.0;
        double root = Math.sqrt(1 - square);
        return sign * root;
    }
}

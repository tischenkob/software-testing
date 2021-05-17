package ru.ifmo.math.trigonometry;

import lombok.RequiredArgsConstructor;

import static java.lang.Math.PI;

@RequiredArgsConstructor
public class Cos implements CosineFunction {

    private final SineFunction sin;

    @Override
    public double of(double x) {
        double sign = (x > PI / 2) ? -1 : 1;
        return sign * Math.sqrt(1 - Math.pow(sin.of(x), 2));
    }
}

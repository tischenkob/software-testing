package ru.ifmo.math.trigonometry;

import lombok.RequiredArgsConstructor;
import ru.ifmo.math.Computable;

@RequiredArgsConstructor
public class Sec implements Computable {
    private final CosineFunction cos;

    @Override
    public double of(double x) {
        return 1 / cos.of(x);
    }
}

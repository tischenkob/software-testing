package ru.ifmo.math.trigonometry;

import lombok.RequiredArgsConstructor;
import ru.ifmo.math.Computable;

@RequiredArgsConstructor
public class Cot implements Computable {
    private final SineFunction sin;
    private final CosineFunction cos;

    @Override
    public double of(double x) {
        return cos.of(x) / sin.of(x);
    }
}

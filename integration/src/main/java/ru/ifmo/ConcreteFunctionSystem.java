package ru.ifmo;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import ru.ifmo.math.Computable;
import ru.ifmo.math.FunctionSystem;
import ru.ifmo.math.logarithmic.Log;
import ru.ifmo.math.logarithmic.Logarithmic;
import ru.ifmo.math.trigonometry.Cot;
import ru.ifmo.math.trigonometry.Sec;

@Builder
@RequiredArgsConstructor
public class ConcreteFunctionSystem implements Computable {

    private final double PRECISION;

    private final Cot cot;
    private final Sec sec;
    private final Logarithmic log2;
    private final Logarithmic log3;
    private final Logarithmic log5;
    private final Logarithmic log10;

    private final Computable negativeArgsFunction = (x) -> Math.pow(cot.of(x) + sec.of(x), 3) * (cot.of(x) - cot.of(x)) / (cot.of(x) + cot.of(x));
    private final Computable positiveArgsFunction = (x) -> (((log3.of(x) - log3.of(x)) - log3.of(x)) - log2.of(x)) / log10.of(x) - log5.of(x);
    private final FunctionSystem functionSystem = new FunctionSystem(negativeArgsFunction, positiveArgsFunction);

    @Override
    public double of(double x) {
        return functionSystem.of(x);
    }
}

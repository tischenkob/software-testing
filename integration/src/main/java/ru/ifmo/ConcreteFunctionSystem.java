package ru.ifmo;

import lombok.Builder;
import ru.ifmo.math.Computable;
import ru.ifmo.math.FunctionSystem;
import ru.ifmo.math.logarithmic.Logarithmic;
import ru.ifmo.math.trigonometry.Cot;
import ru.ifmo.math.trigonometry.Sec;

import static java.lang.Math.pow;

public class ConcreteFunctionSystem implements Computable {

    private final double ACCURACY;
    private final Cot cot;
    private final Sec sec;
    private final Logarithmic log2;
    private final Logarithmic log3;
    private final Logarithmic log5;
    private final Logarithmic log10;

    private final Computable negativeArgsFunction;
    private final Computable positiveArgsFunction;
    private final FunctionSystem functionSystem;

    @Builder
    public ConcreteFunctionSystem(double ACCURACY, Cot cot, Sec sec, Logarithmic log2, Logarithmic log3, Logarithmic log5, Logarithmic log10) {
        this.ACCURACY = ACCURACY;
        this.cot = cot;
        this.sec = sec;
        this.log2 = log2;
        this.log3 = log3;
        this.log5 = log5;
        this.log10 = log10;

        this.negativeArgsFunction = (x) -> {
            var upper = pow(cot.of(x) * sec.of(x), 3) * (cot.of(x) - cot.of(x));
            var lower = (cot.of(x) + cot.of(x));
            return upper / lower;
        };
        this.positiveArgsFunction = (x) -> {
            var upper = (((log3.of(x) - log3.of(x)) - log3.of(x)) - log2.of(x));
            return upper / log10.of(x) - pow(log5.of(x), 2);
        };
        this.functionSystem = new FunctionSystem(negativeArgsFunction, positiveArgsFunction);
    }

    @Override
    public double of(double x) {
        return functionSystem.of(x);
    }
}

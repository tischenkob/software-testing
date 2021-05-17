package ru.ifmo.math.logarithmic;

import ru.ifmo.math.PreciseFunction;
import ru.ifmo.math.logarithmic.Logarithmic;

public class NaturalLog extends PreciseFunction implements Logarithmic {

    public NaturalLog(double PRECISION) {
        super(PRECISION);
    }

    @Override
    public double of(double x) {
        return 0;
    }

}

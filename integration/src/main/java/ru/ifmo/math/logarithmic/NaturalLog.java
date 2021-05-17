package ru.ifmo.math.logarithmic;

import ru.ifmo.math.Computable;
import ru.ifmo.math.AccurateFunction;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class NaturalLog extends AccurateFunction implements Logarithmic {

    private final double ln2 = of(2.0);

    public NaturalLog(double ACCURACY) {
        super(ACCURACY * 0.00000001);
    }

    @Override
    public double of(double x) {
        if (x == 0.0) return Double.NEGATIVE_INFINITY;
        Computable series = n -> pow(x - 1, n) / (n);

        if (x > 2) return of(x / 2.0) + ln2;

        var result = 0.0;
        var current = 10.0;
        var previous = 0.0;
        var n = 1;
        while (abs(previous - current) > this.ACCURACY) {
            previous = current;
            current = series.of(n);
            result += (-1 + n % 2 * 2) * current; // -1 if even, 1 if odd
            n++;
        }
        return result;
    }

}

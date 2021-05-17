package ru.ifmo.math.trigonometry;

import ru.ifmo.math.Computable;
import ru.ifmo.math.PreciseFunction;

import static java.lang.Math.*;


public class Sin extends PreciseFunction implements SineFunction {
    public Sin(double PRECISION) {
        super(PRECISION);
    }

    @Override
    public double of(double x) {
        x = putInBounds(x);
        final double finalX = x;
        Computable series = n -> pow(-1.0, n) * pow(finalX, 2 * n + 1) / fact(2 * n + 1);
        double result = 0;
        double current = 10;
        double previous = 0;
        int n = 0;
        while (abs(previous - current) >= PRECISION) {
            previous = current;
            current = series.of(n);
            result += current;
            n += 1;
        }
        return result;
    }

    private static int fact(double x) {
        return fact((int) x);
    }

    private static int fact(int x) {
        if (x == 1) return x;
        return x * fact(x - 1);
    }

    private static double putInBounds(double x) {
        double aux = x % (2 * PI);
        double sign = signum(aux);
        aux = abs(aux);
        if (aux > PI) aux -= 2 * PI;
        if (sign < 0) aux *= sign;
        return aux;
    }


}

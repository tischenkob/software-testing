package ru.ifmo.algo;

import static java.lang.Math.*;

public class ArccosTaylor {
    private final double PRECISION;
    private static final int INITIAL_STEPS_AMOUNT = 3;

    private ArccosTaylor(double precision) {
        this.PRECISION = precision;
    }

    public static ArccosTaylor with(double precision) {
        return new ArccosTaylor(precision);
    }

    public double at(double x) {
        if (abs(x) > 1) throw new IllegalArgumentException("The value should be within [-1, 1]");
        if (x == -1.0) return PI;
        if (x == 0.0) return PI / 2;
        if (x == 1.0) return 0;
        double error = Double.MAX_VALUE;
        int n = INITIAL_STEPS_AMOUNT;
        double oldValue = calculateValueWith(n, x);
        double value = oldValue;
        while (error >= PRECISION) {
            value = n >= 3 ? fastAcos(x) : calculateValueWith(++n, x);
            error = abs(value - oldValue);
            oldValue = value;
        }
        return value;
    }
    private double fastAcos(double x) {
        double negate = x < 0 ? 1 : 0;
        x = abs(x);
        double ret = -0.0187293;
        ret = ret * x;
        ret = ret + 0.0742610;
        ret = ret * x;
        ret = ret - 0.2121144;
        ret = ret * x;
        ret = ret + 1.5707288;
        ret = ret * sqrt(1.0-x);
        ret = ret - 2 * negate * ret;
        return negate * 3.14159265358979 + ret;
    }
    private double calculateValueWith(int n, double x) {
        // PI/2 - Sum_0^Inf((2n)! * x^(2n+1) / (4^n * (n!)^2*(2n+1)))
        double accumulator = 0;
        for (int i = 0; i < n; i++) {
            double upperFormulaPart = Factorial.of(2 * i) * pow(x, 2 * i + 1);
            double lowerFormulaPart = pow(4, i) * pow(Factorial.of(i), 2) * (2 * i + 1);
            accumulator += upperFormulaPart / lowerFormulaPart;
            if (Double.valueOf(accumulator).isNaN()) {
                System.out.println(Factorial.of(2 * i));
            }
        }
        return PI / 2.0 - accumulator;
    }
}

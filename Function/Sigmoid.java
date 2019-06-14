package Function;

import java.lang.Math;

public final class Sigmoid implements Function {
    public double getOutput(double x) { return formula1(x); }
    public double formula1(double x) { return 1 / (1 + Math.exp(-x)); }
    public double formula2(double x) { return Math.exp(x) / (Math.exp(x) + 1); }
}
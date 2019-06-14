package Util;

import java.util.List;

public final class LinearAlgebra {
    public static double VectorDotProduct(List<Double> x, List<Double> y) {
        double total = 0;
        for(int i=0; i < x.size(); i++) {
            total += x.get(i) * y.get(i);
        }
        return total;
    }
}
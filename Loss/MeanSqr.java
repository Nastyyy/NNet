package Loss;

public final class MeanSqr implements Loss { 
    public static final long serialVersionUID = 200L;
    public double getOutput(double truth, double predicted) { return Math.pow(truth-predicted, 2); }
}

package Function;

public final class Relu implements Function {
    private static final long serialVersionUID = 101L;
    public double getOutput(double x) {
        return x*2;
    }
}
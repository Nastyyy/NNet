import java.util.ArrayList;

public class Connection {
    private Neuron neuron;
    private double weight;
    private double bias;

    public Connection() {
        this.weight = 0.0;
        this.bias = 0.0;
    }

    public double getWeight() { return this.weight; }
    public double getBias() { return this.bias; }
    public void setWeight(double weight) { this.weight = weight; }
    public void setBias(double bias) { this.bias = bias; }
}
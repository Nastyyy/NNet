import java.util.ArrayList;

public class Weight {
    private Neuron genNeuron;
    private ArrayList<Neuron> connNerons;
    private double weight;
    private double bias;

    public Weight(Neuron genNeuron, double weight, double bias) {
        this.genNeuron = genNeuron;
        this.weight = weight; 
        this.bias = bias;
    }

    public double getWeight() { return this.weight; }
    public double getBias() { return this.bias; }
    public Neuron getNeuron() { return this.genNeuron; }

    public void setWeight(double weight) { this.weight=weight; }
    public void setBias(double bias) { this.bias=bias; }
    public void setNeuron(Neuron neuron) { this.genNeuron=neuron; }
}

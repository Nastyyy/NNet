import java.util.ArrayList;

public class Connection {
    private Neuron sourceNeuron;
    private Neuron targetNeuron;
    private double signal;
    private double weight;

    public Connection(Neuron source, Neuron target) {
        this.sourceNeuron = source;

        // Creates connection between sourceNeuron and targetNeuron
        // Don't create connection if target is null (on input layers)
        if(target != null) { 
            this.targetNeuron = target;
            this.targetNeuron.AddInConnection(this);
        }

        this.weight = 0.0;
    }

    public Neuron getSourceNeuron() { return this.sourceNeuron; }
    public Neuron getTargetNeuron() { return this.targetNeuron; }
    public double getSignal() { return this.signal; }
    public Connection setSignal(double signal) { this.signal = signal; return this;}
    public double getWeight() { return this.weight; }
    public Connection setWeight(double weight) { this.weight = weight; return this; }
}
import java.util.ArrayList;

import Function.Function;

enum LayerType { Input, Hidden, Output }

public class Layer {
    private ArrayList<Neuron> neurons = new ArrayList<Neuron>();
    private LayerType type;
    private Function function;

    // If input layer
    private double[] inputData;
    
    public Layer(int size, Function function, LayerType type) {
        this.type = type;
        this.function = function; 
        this.addNeurons(size);
    }

    public Layer(double[] inputData, Function function) {
        this.type = LayerType.Input;
        this.function = function;
        this.inputData = inputData;
        this.createInputConnections();
    }

    public void ActivateNeurons() {
        for(Neuron neuron: this.getNeurons()) {
            neuron.Activate();
        }
    }

    // Only used when making input layer
    // Creates connections that have no weight 
    public void createInputConnections() {
        for(int i=0; i < this.inputData.length; i++) {
            Neuron neuron = new Neuron(this.function);
            Connection conn = new Connection(neuron, null);
            conn.setSignal(inputData[i]);
            neuron.inConnections.add(conn);
            this.neurons.add(neuron);
        }
    }

    public void ConnectLayerToLayer(Layer targetLayer) {
        for(Neuron source: this.neurons) {
            source.CreateConnectionsToLayer(targetLayer);
        }
    }

    private void addNeurons(int amount) {
        for(int i=0; i < amount; i++) {
            neurons.add(new Neuron(this.function));
        }
    }

    public ArrayList<Neuron> getNeurons() { return this.neurons; }
    public LayerType getType() { return this.type; }
}
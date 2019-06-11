import java.util.ArrayList;

import Function.Function;

enum LayerType { Input, Hidden, Output }

public class Layer {
    private ArrayList<Neuron> neurons = new ArrayList<Neuron>();
    private LayerType type;
    
    public Layer(int size, Function function, LayerType type) {
        this.type = type;
        for(int i=0; i < size; i++) {
            neurons.add(new Neuron(function));
        }
    }

    public ArrayList<Neuron> getNeurons() { return this.neurons; }
    public LayerType getType() { return this.type; }
}
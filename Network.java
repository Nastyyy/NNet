import java.util.ArrayList;

import Function.Function;

public class Network {
    private ArrayList<Layer> layers = new ArrayList<Layer>();
    private double[] input;

    public Network addInputData(double[] input) { this.input = input; return this; }

    public Network addInputLayer(double[] inputData, Function function) {
        layers.add(new Layer(inputData, function));
        return this;
    }

    public Network addHiddenLayer(int size, Function function) {
        layers.add(new Layer(size, function, LayerType.Hidden));
        layers.get(layers.size()-2).ConnectLayerToLayer(layers.get(layers.size()-1));
        return this;
    }

    public Network addOutputLayer(int size, Function function) {
        layers.add(new Layer(size, function, LayerType.Output));
        layers.get(layers.size()-2).ConnectLayerToLayer(layers.get(layers.size()-1));
        return this;
    }

    public Network Run() {
        for(Layer layer: this.layers) {
            // Set Input Layer's Neurons Connections Signals to inputData
            layer.ActivateNeurons();             
        }
        return this;
    }

    public ArrayList<Layer> getLayers() { return this.layers; }
    public int getLayersSize() { return this.layers.size(); }
}

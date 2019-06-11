import java.util.ArrayList;

import Function.Function;

public class Network {
    private ArrayList<Layer> layers = new ArrayList<Layer>();

    public Network addInputLayer(int size, Function function) {
        layers.add(new Layer(size, function, LayerType.Input));
        return this;
    }

    public Network addHiddenLayer(int size, Function function) {
        layers.add(new Layer(size, function, LayerType.Hidden));
        return this;
    }

    public Network addOutputLayer(int size, Function function) {
        layers.add(new Layer(size, function, LayerType.Output));
        return this;
    }

    public ArrayList<Layer> getLayers() { return this.layers; }
    public int getLayersSize() { return this.layers.size(); }
}

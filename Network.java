import java.io.Serializable;
import java.util.ArrayList;

import Function.Function;
import Loss.Loss;

public class Network implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<Layer> layers = new ArrayList<Layer>();
    private Loss lossFunction;

    public Network(Loss lossFunction) {
        this.lossFunction = lossFunction;
    }

    public double getLoss(double[] truths) { 
        double output = 0;
        Layer outputLayer = this.getLayerAt(this.getLayersSize()-1);
        for(int i=0; i < outputLayer.getNeurons().size(); i++) {
            output += this.lossFunction.getOutput(truths[i], outputLayer.getNeuronAt(i).getSignal());
        }

        return output;
    }

    public Network addInputLayer(double[] inputData, Function function) {
        layers.add(new Layer(inputData, function));
        return this;
    }

    public Network addHiddenLayer(int size, Function function) {
        layers.add(new Layer(size, function, LayerType.Hidden));
        this.getLayerAt(this.getLayersSize()-2).ConnectLayerToLayer(this.getLayerAt(this.getLayersSize()-1));
        return this;
    }

    public Network addOutputLayer(int size, Function function) {
        layers.add(new Layer(size, function, LayerType.Output));
        this.getLayerAt(layers.size()-2).ConnectLayerToLayer(this.getLayerAt(this.getLayersSize()-1));
        return this;
    }

    public Network Run() {
        for(Layer layer: this.layers) {
            layer.ActivateNeurons();             
        }
        return this;
    }

    public Network setInputData(double[] inputData) {
        Layer inputLayer = this.getLayerAt(0);
        for(int i=0; i < inputData.length; i++) {
            inputLayer.getNeuronAt(i).getInConnectionAt(0).setSignal(inputData[i]);
        }
        return this;
    }

    public Layer getLayerAt(int i) { return this.getLayers().get(i); }
    public ArrayList<Layer> getLayers() { return this.layers; }
    public int getLayersSize() { return this.layers.size(); }
}

import Function.*;

public class Networks {
    public static Network XORnet() {
        double[] inputData = {1.0,1.0};

        Network net = new Network()
            .addInputLayer(inputData, new Input())
            .addHiddenLayer(2, new Sigmoid())
            .addOutputLayer(1, new Sigmoid()
        );

        net.getLayerAt(1).getNeuronAt(0).setBias(-10.0);
        net.getLayerAt(1).getNeuronAt(1).setBias(30.0);
        net.getLayerAt(2).getNeuronAt(0).setBias(-30.0);
        
        for(Neuron neuron: net.getLayerAt(0).getNeurons()) {
            neuron.getOutConnectionAt(0).setWeight(20.0);
            neuron.getOutConnectionAt(1).setWeight(-20.0);
        }

        for(Neuron neuron: net.getLayerAt(1).getNeurons()) {
            neuron.getOutConnectionAt(0).setWeight(20.0);
        }

        return net;
    }
}
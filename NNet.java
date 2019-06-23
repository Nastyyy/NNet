import Function.*;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.lang.Math;

public class NNet {

    public static final String fileExtension = "nnet";
    public static void main(String args[]) {
        double[] inputData = {1.0,1.0};

        // TODO: Learn
        Network net = new Network()
                .addInputLayer(inputData, new Input())
                .addHiddenLayer(2, new Sigmoid())
                .addOutputLayer(1, new Sigmoid()
        );


        // Set neurons bias
        net.getLayers().get(1).getNeurons().get(0).setBias(-10.0);
        net.getLayers().get(1).getNeurons().get(1).setBias(30.0);
        net.getLayers().get(2).getNeurons().get(0).setBias(-30.0);

        // Set weights
        for(Neuron neuron: net.getLayers().get(0).getNeurons()) {
            //System.out.println(neuron.getOutConnections().get(0).getTargetNeuron());
            neuron.getOutConnections().get(0).setWeight(20.0);
            neuron.getOutConnections().get(1).setWeight(-20.0);
        }

        for(Neuron neuron: net.getLayers().get(1).getNeurons()) {
            neuron.getOutConnections().get(0).setWeight(20.0);
        }

        printNetwork(net);
        System.out.println("\n");
        printNetworkSignals(net);

        net.Run();
        saveNetwork(net, "XORnet");
    }

    public static void printNetworkSignals(Network net) {
        for(Layer layer: net.getLayers()) {
            System.out.println("------ " + layer.getType() + " ------");
            for(Neuron neuron: layer.getNeurons()) {
                System.out.println(neuron + " -> " + Math.round(neuron.getSignal()));
            }
        }
    }

    public static void saveNetwork(Network net, String file) {
        System.out.println("\n              Saving network as " + file + ".nnet...            \n");
        try {
            FileOutputStream fos = new FileOutputStream(file + "." + fileExtension);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(net);
            oos.close();
            fos.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Network loadNetwork(String path) {
        try {
			FileInputStream fis = new FileInputStream(path + "." + fileExtension);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Network net = (Network) ois.readObject();
			ois.close();
            return net;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void printNetwork(Network net) {
        for(Layer layer: net.getLayers()) {
            System.out.println("------ " + layer.getType() + " -----");
            for(Neuron neuron: layer.getNeurons()) {
                System.out.print("     " + neuron);
                if(layer.getType() != LayerType.Output) { 
                    System.out.print(" -> ");
                }
                for(Connection conn: neuron.getOutConnections()) {
                    System.out.print(conn.getTargetNeuron() + ", ");
                }
                System.out.print("\n");
            }
        }
    }
}
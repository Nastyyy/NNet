import Function.*;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.lang.Math;

public class NNet {
    public static void main(String args[]) {
        double[] inputData = {1.0,1.0};

        // TODO: Learn
        Network net = new Network()
                .addInputLayer(inputData, new Input())
                .addHiddenLayer(2, new Sigmoid())
                .addOutputLayer(1, new Sigmoid()
        );

        printNet(net);

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

        System.out.println("\n\n");

        // Now run
        net.Run();

        for(Neuron neuron: net.getLayers().get(0).getNeurons()) {
            for(Connection conn: neuron.getInConnections()) {
                System.out.println(conn.getSignal());
            }
        }

        //System.out.println(net.getLayers().get(0).getNeurons().get(0).getInConnections());

        for(Layer layer: net.getLayers()) {
            System.out.println("------ " + layer.getType() + " ------");
            for(Neuron neuron: layer.getNeurons()) {
                System.out.println(neuron + " -> " + Math.round(neuron.getSignal()));
            }
        }

        saveNetwork(net);

    }

    public static void saveNetwork(Network net) {
        System.out.println("------------- Saving network... ----------------");
        try {
            FileOutputStream fos = new FileOutputStream("network.nnet");
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
			FileInputStream fis = new FileInputStream(path);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Network net = (Network) ois.readObject();
			ois.close();
            return net;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void printNet(Network net) {
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
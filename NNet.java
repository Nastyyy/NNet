import Function.*;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.lang.Math;

public class NNet {

    public static final String fileExtension = "nnet";
    public static final String networkFile = "XORnet";
    public static void main(String args[]) {
        
        Network net = Networks.XORnet();

        net.Run();
        printNetwork(net);
        System.out.println("\n");
        printNetworkSignals(net);
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


}
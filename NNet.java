import Function.*;
import Util.LinearAlgebra;
import java.util.ArrayList;
import java.util.List;

public class NNet {
    public static void main(String args[]) {
        double[] inputData = {1.0,1.0};

        // TODO: Run and Learn
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
                System.out.println(conn + " " + conn.getSourceNeuron() + " " + conn.getTargetNeuron());
                System.out.println(conn.getSignal());
            }
        }
        //System.out.println(net.getLayers().get(0).getNeurons().get(0).getInConnections());

        for(Layer layer: net.getLayers()) {
            System.out.println("------ " + layer.getType() + " ------");
            for(Neuron neuron: layer.getNeurons()) {
                System.out.println(neuron + " -> " + neuron.getSignal());
            }
        }

        System.out.println("-----------------------------");
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
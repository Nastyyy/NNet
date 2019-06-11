import Function.*;

public class NNet {
    public static void main(String args[]) {
        Network net = new Network()
                .addInputLayer(3, new Input())
                .addHiddenLayer(4, new Relu())
                .addOutputLayer(3, new Output());
        for(Layer layer: net.getLayers()) {
            System.out.println("------ " + layer.getType() + " -----");
            for(Neuron neuron: layer.getNeurons()) {
                System.out.println("------" + neuron + "------");
                for(Connection conn: neuron.getOutConnections()) {
                    System.out.println(conn);
                }
            }
        }
    }
}
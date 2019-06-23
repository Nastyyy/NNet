import java.io.Serializable;
import java.util.ArrayList;

import Function.Function;
import Util.LinearAlgebra;

public class Neuron implements Serializable {
    private static final long serialVersionUID = 3L;
    ArrayList<Connection> inConnections = new ArrayList<Connection>();
    ArrayList<Connection> outConnections = new ArrayList<Connection>();
    Function function;
    private double bias = 0.0;


    public Neuron(Function function) {
        this.function = function;
    }

    public void Activate() {
        for(Connection outConn: this.getOutConnections()) {
            outConn.setSignal(this.getSignal());
        }
    }

    public double getSignal() {
        return this.function.getOutput(this.getInputSum());        
    }

    private double getInputSum() {
        ArrayList<Double> inWeights = new ArrayList<Double>();
        ArrayList<Double> inSignals = new ArrayList<Double>();

        for(Connection conn: this.inConnections) {
            inWeights.add(conn.getWeight());
            inSignals.add(conn.getSignal());
        }

        return (LinearAlgebra.VectorDotProduct(inWeights, inSignals) + this.bias);
    }


    public ArrayList<Connection> CreateConnectionsToLayer(Layer targetLayer) {
        for(Neuron neuron: targetLayer.getNeurons()) {
            this.CreateConnection(neuron);
        }
        return this.getOutConnections();
    }

    private Connection CreateConnection(Neuron targetNeuron) {
        Connection connection = new Connection(this, targetNeuron); 
        this.outConnections.add(connection);
        return connection;
    }

    public void AddInConnection(Connection inConnection) {
        this.inConnections.add(inConnection);
    }

    public Connection getInConnectionAt(int i) { return this.getInConnections().get(i); }
    public Connection getOutConnectionAt(int i) { return this.getOutConnections().get(i); }
    public ArrayList<Connection> getInConnections() { return this.inConnections; }
    public ArrayList<Connection> getOutConnections() { return this.outConnections; }
    public double getBias() { return this.bias; }
    public Neuron setBias(double bias) { this.bias =bias; return this; }
}
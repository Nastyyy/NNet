import java.util.ArrayList;

import Function.Function;
import Util.LinearAlgebra;

public class Neuron {
    ArrayList<Connection> inConnections = new ArrayList<Connection>();
    ArrayList<Connection> outConnections = new ArrayList<Connection>();
    Function function;
    private double bias = 0.0;
    private boolean input = false;


    public Neuron(Function function) {
        this.function = function;
    }

    public Neuron(Function function, boolean input) {
        this.function = function;
        this.input = input;
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

        if(!input) {
            return (LinearAlgebra.VectorDotProduct(inWeights, inSignals) + this.bias);
        } else {
            return inSignals.get(0);
        }
    }

    public ArrayList<Connection> CreateConnectionsToLayer(Layer targetLayer) {
        for(Neuron neuron: targetLayer.getNeurons()) {
            //System.out.println("Connection being made: " + this + " --> " + neuron);
            this.CreateConnection(neuron);
        }
        //System.out.println("---------------------------------------");
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

    public ArrayList<Connection> getInConnections() { return this.inConnections; }
    public ArrayList<Connection> getOutConnections() { return this.outConnections; }
    public double getBias() { return this.bias; }
    public Neuron setBias(double bias) { this.bias =bias; return this; }
}
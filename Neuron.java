import java.util.ArrayList;

import Function.Function;

public class Neuron {
    ArrayList<Connection> inConnections = new ArrayList<Connection>();
    ArrayList<Connection> outConnections = new ArrayList<Connection>();
    Function function;


    public Neuron(Function function) {
        this.function = function;
    }

    public ArrayList<Connection> getInConnections() { return this.inConnections; }
    public ArrayList<Connection> getOutConnections() { return this.outConnections; }
}
package Loss;

import java.io.Serializable;

public interface Loss extends Serializable {
    public double getOutput(double truth, double netOutput);
}
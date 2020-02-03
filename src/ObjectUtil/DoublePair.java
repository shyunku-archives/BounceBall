package ObjectUtil;

public class DoublePair {
    public double d1;
    public double d2;

    public DoublePair(double d1, double d2){
        this.d1 = d1;
        this.d2 = d2;
    }

    public double getDistance(DoublePair other){
        return Math.sqrt(Math.pow(other.d1 - d1, 2) + Math.pow(other.d2 - d2, 2));
    }
}

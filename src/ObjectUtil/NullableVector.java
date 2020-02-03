package ObjectUtil;

public class NullableVector {
    public double vect;
    private boolean touched;

    public NullableVector(){
        touched = false;
    }

    public NullableVector(double x, double y){
        vect = Math.atan2(y, x);

        touched = true;
    }

    public boolean isNull(){
        return !touched;
    }
}

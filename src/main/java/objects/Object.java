package objects;

public class Object {
    public double volume;

    public Object(double volume) {
        this.volume = volume;
    }

    public Object(double a, double b, double c) {
        this.volume = a * b * c;
    }
}

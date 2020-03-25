package objects;

public class Vehicle extends Item {
    public double engineCapacity;
    public String engineType;
    public String type;
    public static int index = 0;

    public Vehicle(double volume, String title, double engineCapacity, String engineType, String type) {
        super(volume, title);
        this.engineCapacity = engineCapacity;
        this.engineType = engineType;
        this.type = type;
        super.id = "V-" + ++index;
    }

    public Vehicle(double a, double b, double c, String title, double engineCapacity, String engineType, String type) {
        super(a, b, c, title);
        this.engineCapacity = engineCapacity;
        this.engineType = engineType;
        this.type = type;
        super.id = "V-" + ++index;
    }

    public String toString(){
        return super.toString() + " - Vehicle type: " + type + "\n" +
                " - Engine capacity: " + engineCapacity + "\n" +
                " - Engine type: " + engineType + "\n";
    }
}

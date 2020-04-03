package objects.vehicles;

import objects.Vehicle;

public class Boat extends Vehicle {
    public double sailSpace;

    public Boat(double volume, String title, double engineCapacity, String engineType, String type, double sailSpace) {
        super(volume, title, engineCapacity, engineType, type);
        this.sailSpace = sailSpace;
    }

    public Boat(double a, double b, double c, String title, double engineCapacity, String engineType, String type, double sailSpace) {
        super(a, b, c, title, engineCapacity, engineType, type);
        this.sailSpace = sailSpace;
    }
}

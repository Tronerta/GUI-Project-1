package objects.vehicles;

import objects.Vehicle;

public class Motorcycle extends Vehicle {
    public double maxSpeed;

    public Motorcycle(double volume, String title, double engineCapacity, String engineType, String type, double maxSpeed) {
        super(volume, title, engineCapacity, engineType, type);
        this.maxSpeed = maxSpeed;
    }

    public Motorcycle(double a, double b, double c, String title, double engineCapacity, String engineType, String type, double maxSpeed) {
        super(a, b, c, title, engineCapacity, engineType, type);
        this.maxSpeed = maxSpeed;
    }
}

package vehicles;

import objects.Vehicle;

public class Amphibian extends Vehicle {
    public double waterSpeed;

    public Amphibian(double volume, String title, double engineCapacity, String engineType, String type, double waterSpeed) {
        super(volume, title, engineCapacity, engineType, type);
        this.waterSpeed = waterSpeed;
    }

    public Amphibian(double a, double b, double c, String title, double engineCapacity, String engineType, String type, double waterSpeed) {
        super(a, b, c, title, engineCapacity, engineType, type);
        this.waterSpeed = waterSpeed;
    }
}

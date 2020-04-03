package objects.vehicles;

import objects.Vehicle;

public class OutdoorCar extends Vehicle {
    public boolean fourWheelDrive;

    public OutdoorCar(double volume, String title, double engineCapacity, String engineType, String type, boolean fourWheelDrive) {
        super(volume, title, engineCapacity, engineType, type);
        this.fourWheelDrive = fourWheelDrive;
    }

    public OutdoorCar(double a, double b, double c, String title, double engineCapacity, String engineType, String type, boolean fourWheelDrive) {
        super(a, b, c, title, engineCapacity, engineType, type);
        this.fourWheelDrive = fourWheelDrive;
    }
}

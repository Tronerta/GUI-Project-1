package objects.vehicles;

import objects.Vehicle;

public class CityCar extends Vehicle {
    public double gasConsumptionPerKm;

    public CityCar(double volume, String title, double engineCapacity, String engineType, String type, double gasConsumptionPerKm) {
        super(volume, title, engineCapacity, engineType, type);
        this.gasConsumptionPerKm = gasConsumptionPerKm;
    }

    public CityCar(double a, double b, double c, String title, double engineCapacity, String engineType, String type, double gasConsumptionPerKm) {
        super(a, b, c, title, engineCapacity, engineType, type);
        this.gasConsumptionPerKm = gasConsumptionPerKm;
    }
}

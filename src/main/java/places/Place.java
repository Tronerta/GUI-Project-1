package places;

import objects.Object;
import person.Person;

import java.time.LocalDate;

public class Place extends Object {
    public Person tenant;
    public LocalDate startDate;
    public LocalDate endDate;
    public String id;

    public Place(double volume) {
        super(volume);
    }

    public Place(double a, double b, double c) {
        super(a, b, c);
    }

    public boolean isAvaliable() {
        return this.tenant == null;
    }

    public void setTenant(Person person) {
        this.tenant = person;
    }

    public String toString() {
        String result =  "Place ID: " + id + "\n" +
                " - Type: " + (this instanceof Apartment ? "Apartment" : "Parking Spot") + "\n" +
                " - Volume: " + volume + "\n";
        if (tenant != null) {
            result += " - Tenant: " + tenant.toString() + "\n" +
                    " - Rent start date: " + startDate + "\n" +
                    " - Rent end date: " + endDate + "\n";
        }
        return result;
    }

    public void clean(){
        tenant.places.remove(this);
        tenant = null;
        startDate = null;
        endDate = null;
    }

}

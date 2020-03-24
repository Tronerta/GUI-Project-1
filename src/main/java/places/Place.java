package places;

import objects.Object;
import person.Person;

import java.time.LocalDate;

public class Place extends Object {
    Person najemca;
    public LocalDate startDate;
    public LocalDate endDate;
    public String id;
    private static int index = 0;

    public Place(double volume) {
        super(volume);
    }

    public Place(double a, double b, double c) {
        super(a, b, c);
    }

    public boolean isAvaliable() {
        return this.najemca == null;
    }

    public void setNajemca(Person person) {
        this.najemca = person;
    }

}

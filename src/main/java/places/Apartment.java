package places;

import person.Person;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Apartment extends Place {
    public List<Person> habitants = new ArrayList<Person>();
    private static int index = 0;

    public Apartment(double volume) {
        super(volume);
        super.id = "M" + ++index;
    }


}

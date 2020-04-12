package places;

import person.Person;
import java.util.ArrayList;
import java.util.List;

public class Apartment extends Place {
    public List<Person> habitants = new ArrayList<>();
    private static int index = 0;

    public Apartment(double volume) {
        super(volume);
        super.id = "A-" + ++index;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(super.toString());
        if (!habitants.isEmpty()){
            result.append(" * Habitants: \n");
            for (Person p : habitants){
                result.append("    ").append(p.toString());
            }
        }
        return result.toString();
    }

    public void clean(){
        super.clean();
        habitants.clear();
    }
}

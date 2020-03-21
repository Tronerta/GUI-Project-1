package objects;

import java.util.ArrayList;
import java.util.List;

public class Mieszkanie extends Pomieszczenie {
    List<Osoba> mieszkancy = new ArrayList<Osoba>();
    private static int index = 0;

    public Mieszkanie(double volume) {
        super(volume);
        super.id = "M" + ++index;
    }


}

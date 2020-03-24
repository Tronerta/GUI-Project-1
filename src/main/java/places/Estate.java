package places;

import java.util.ArrayList;
import java.util.List;

public class Estate {
    private List<Place> places = new ArrayList<>();

    public Estate(List<Place> places) {
        this.places = places;
    }

    public Place find(String id) {
        for (Place p : this.places) {
            if (p.id.equals(id))
                return p;
        }
        return null;
    }
}

package places;

import java.util.ArrayList;
import java.util.List;

public class Estate {
    public List<Apartment> apartments = new ArrayList<>();
    public List<ParkingSpot> parkings = new ArrayList<>();

    public Estate(List<Apartment> apartments, List<ParkingSpot> parkings) {
        this.apartments = apartments;
        this.parkings = parkings;
    }
//
//    public Place find(String id) {
//        for (Place p : this.places) {
//            if (p.id.equals(id))
//                return p;
//        }
//        return null;
//    }
}

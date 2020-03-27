package places;

import objects.Item;
import person.Person;

import java.util.ArrayList;
import java.util.List;

public class Estate {
    public List<Apartment> apartments;
    public List<ParkingSpot> parkings;
    public List<Item> items;
    public List<Person> people;

    public Estate(List<Apartment> apartments, List<ParkingSpot> parkings, List<Item> items, List<Person> people) {
        this.apartments = apartments;
        this.parkings = parkings;
        this.items = items;
        this.people = people;
    }

    public List<Place> getFreePlaces(){
        List<Place> freePlaces = new ArrayList<>(getFreeApartments());
        freePlaces.addAll(getFreeParkingSpots());
        return freePlaces;
    }

    public List<Apartment> getFreeApartments(){
        List<Apartment> freeApartments = new ArrayList<>();
        for (Apartment a : apartments)
            if (a.isAvaliable())
                freeApartments.add(a);
        return freeApartments;
    }

    public List<ParkingSpot> getFreeParkingSpots(){
        List<ParkingSpot> freeParkingSpots = new ArrayList<>();
        for (ParkingSpot ps : parkings)
            if (ps.isAvaliable())
                freeParkingSpots.add(ps);
        return freeParkingSpots;
    }

    public List<Item> getFreeItems(){
        List<Item> items = new ArrayList<>();
        for (Item i : this.items)
            if (!i.placed)
                items.add(i);
        return items;
    }
}

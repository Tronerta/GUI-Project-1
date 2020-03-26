package places;

import objects.Item;
import person.Person;

import java.util.ArrayList;
import java.util.List;

public class Estate {
    public List<Apartment> apartments = new ArrayList<>();
    public List<ParkingSpot> parkings = new ArrayList<>();
    public List<Item> items = new ArrayList<>();
    public List<Person> people = new ArrayList<>();

    public Estate(List<Apartment> apartments, List<ParkingSpot> parkings, List<Item> items, List<Person> people) {
        this.apartments = apartments;
        this.parkings = parkings;
        this.items = items;
        this.people = people;
    }

    public Person findPerson(String id) {
        for (Person p : people) {
            if (p.id.equals(id))
                return p;
        }
        return null;
    }

    public Place findPlace(String id) {
        for (Apartment a : apartments) {
            if (a.id.equals(id))
                return a;
        }
        for (ParkingSpot ps: parkings){
            if (ps.id.equals(id))
                return ps;
        }
        return null;
    }

    public ParkingSpot findParkingSpot(String id){
        for (ParkingSpot ps: parkings){
            if (ps.id.equals(id))
                return ps;
        }
        return null;
    }

    public Item findItem(String id){
        for (Item i : items){
            if (i.id.equals(id))
                return i;
        }
        return null;
    }

    public void showFreePlaces() {
        System.out.println("Free Apartments: ");
        for (Apartment a : apartments) {
            if (a.isAvaliable()) {
                System.out.println(a);
            }
        }

        System.out.println("Free Parking Spots: ");
        for (ParkingSpot ps : parkings) {
            if (ps.isAvaliable()) {
                System.out.println(ps);
            }
        }
    }
}

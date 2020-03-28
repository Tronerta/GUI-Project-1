package places;

import helpers.DateHelper;
import objects.Item;
import person.Person;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    public List<Place> getFreePlaces() {
        List<Place> freePlaces = new ArrayList<>(getFreeApartments());
        freePlaces.addAll(getFreeParkingSpots());
        return freePlaces;
    }

    public List<Apartment> getFreeApartments() {
        List<Apartment> freeApartments = new ArrayList<>();
        for (Apartment a : apartments)
            if (a.isAvaliable())
                freeApartments.add(a);
        return freeApartments;
    }

    public List<ParkingSpot> getFreeParkingSpots() {
        List<ParkingSpot> freeParkingSpots = new ArrayList<>();
        for (ParkingSpot ps : parkings)
            if (ps.isAvaliable())
                freeParkingSpots.add(ps);
        return freeParkingSpots;
    }

    public List<Item> getFreeItems() {
        List<Item> items = new ArrayList<>();
        for (Item i : this.items)
            if (!i.placed)
                items.add(i);
        return items;
    }

    public void saveReport() {
        try {
            FileWriter file = new FileWriter("report_" + DateHelper.todayDate.toString() + ".txt");
            file.write("ESTATE REPORT \n");
            for (Person p : people) {
                p.places.sort((lhs, rhs) -> Double.compare(rhs.volume, lhs.volume));

            }
            file.write("Files in Java might be tricky, but it is fun enough!");
            file.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

package places;

import helpers.DateHelper;
import objects.Item;
import person.Person;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Estate {
    public List<Apartment> apartments;
    public List<ParkingSpot> parkings;
    public List<Place> places;
    public List<Item> items;
    public List<Person> people;

    public Estate(List<Apartment> apartments, List<ParkingSpot> parkings, List<Item> items, List<Person> people) {
        this.apartments = apartments;
        this.parkings = parkings;
        this.places = new ArrayList<>(apartments);
        this.places.addAll(parkings);
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
            FileWriter file = new FileWriter("reports/report_" + DateHelper.todayDate.toString() + ".txt");
            file.write("ESTATE REPORT \n");
            file.write("People: \n");
            file.write("---------------------\n");
            for (Person p : people) {
                p.places.sort((lhs, rhs) -> Double.compare(rhs.volume, lhs.volume));
                for (Place parking : p.getParkingSpots()) {
                    ParkingSpot ps = (ParkingSpot) parking;
                    ps.items.sort((lhs, rhs) -> Double.compare(rhs.volume, lhs.volume));
                }
                file.write(p.toInfoString());
                file.write("---------------------\n");
            }

            file.write("Free Apartments: \n");
            file.write("---------------------\n");
            List<Apartment> freeApartments = getFreeApartments();
            freeApartments.sort((lhs, rhs) -> Double.compare(rhs.volume, lhs.volume));
            for (Apartment a : freeApartments) {
                file.write(a.toString());
                file.write("---------------------\n");
            }

            file.write("Free Parking Spots: \n");
            file.write("---------------------\n");
            List<ParkingSpot> freeParkingSpots = getFreeParkingSpots();
            freeParkingSpots.sort((lhs, rhs) -> Double.compare(rhs.volume, lhs.volume));
            for (ParkingSpot ps : freeParkingSpots) {
                file.write(ps.toString());
                file.write("---------------------\n");
            }

            file.write("Free Items: \n");
            file.write("---------------------\n");
            List<Item> freeItems = getFreeItems();
            freeItems.sort((lhs, rhs) -> Double.compare(rhs.volume, lhs.volume));
            for (Item ps : freeItems) {
                file.write(ps.toString());
                file.write("---------------------\n");
            }
            
            file.close();
            System.out.println("Success! Report will be avaliable in /reports folder.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

package person;

import exceptions.*;
import helpers.DateHelper;
import objects.Item;
import objects.Vehicle;
import places.ParkingSpot;
import places.Apartment;
import places.Place;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Person {
    public String name;
    public String surname;
    public String PESEL;
    public String address;
    public LocalDate birthDate;

    List<File> files = new ArrayList<>();
    List<Place> places = new ArrayList<>();
    private static int index = 0;
    public String id;

    public Person(String name, String surname, String PESEL, String address, LocalDate birthDate) {
        this.name = name;
        this.surname = surname;
        this.PESEL = PESEL;
        this.address = address;
        this.birthDate = birthDate;
        this.id = "P-" + ++index;
    }

    @Override
    public String toString() {
        return "Information about " + name + " " + surname + ": \n" +
                " - ID: " + id + "\n" +
                " - PESEL: " + PESEL + "\n" +
                " - Address: " + address + "\n" +
                " - Birth date: " + birthDate + "\n" +
                " - Number of Files: " + files.size() + "\n" +
                " - Places: " + places + "\n";
    }

    public String toSmallString(){
        return id + " " + name + " " + surname;
    }

    // Rent a place
    public void rent(Place p) throws NotAvaliableException, TooManyThingsException {
        if ((p instanceof ParkingSpot) && !this.hasApartment()) {
            throw new NotAvaliableException("Rent an apartment first!");
        } else {
            if (p.isAvaliable()) {
                if (this.rentingSpaceAvaliable()) {
                    places.add(p);
                    p.setNajemca(this);
                    p.startDate = DateHelper.randomDate();
                    p.endDate = p.startDate.plusDays(30);
                } else {
                    throw new TooManyThingsException("You have too much rented places");
                }
            } else {
                throw new NotAvaliableException("That apartment is already rented.");
            }
        }
    }

    // Add Person to the apartment
    public void addPerson(Apartment m, Person o) throws NotAvaliableException {
        if (this.places.contains(m)) {
            m.habitants.add(o);
        } else {
            throw new NotAvaliableException("You have no access to that apartment!");
        }
    }

    // Add item to the parking spot
    public void addItem(ParkingSpot parking, Item p) throws NotAvaliableException, TooManyThingsException {
        if (p.placed){
            throw new NotAvaliableException("This item is already in another place!");
        } else if (parking.getFreeVolume() < p.volume ) {
            throw new TooManyThingsException("Remove some old items to insert a new item");
        } else {
            parking.items.add(p);
            p.placed = true;
        }
    }

    public void addItem(ParkingSpot parking, Vehicle p) throws NotAvaliableException, TooManyThingsException {
        if (p.placed){
            throw new NotAvaliableException("This item is already in another place!");
        } else if (parking.getFreeVolume() < p.volume ) {
            throw new TooManyThingsException("Remove some old items to insert a new item");
        } else {
            parking.items.add(p);
            p.placed = true;
        }
    }

    public void removeItem(ParkingSpot mp, Item p) throws NotAvaliableException {
        if (mp.items.contains(p)){
            mp.items.remove(p);
            p.placed = false;
        } else {
            throw new NotAvaliableException("This item is not in that parking spot!");
        }
    }


    private boolean rentingSpaceAvaliable() {
        return this.places.size() < 5;
    }

    private boolean hasApartment() {
        for (Place p : this.places) {
            if (p instanceof Apartment)
                return true;
        }
        return false;
    }
}

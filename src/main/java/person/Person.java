package person;

import exceptions.*;
import helpers.DateHelper;
import objects.Item;
import places.ParkingSpot;
import places.Apartment;
import places.Place;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Person {
    public String name;
    public String surname;
    public String PESEL;
    public String address;
    public Date birthDate;

    List<File> files = new ArrayList<>();
    List<Place> places = new ArrayList<>();
    private static int index = 0;
    public String id;

    public Person(String name, String surname, String PESEL, String address, Date birthDate) {
        this.name = name;
        this.surname = surname;
        this.PESEL = PESEL;
        this.address = address;
        this.birthDate = birthDate;
        this.id = "O" + ++index;
    }

    // Wynajecie pomieszczenia
    private void rent(Place p) throws NotAvaliableException, TooManyThingsException {
        if ((p.getClass() == ParkingSpot.class) && !this.hasApartment()) {
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

    // Dodanie mieszkanca do mieszkania
    public void checkInPerson(Apartment m, Person o) throws NotAvaliableException {
        if (this.places.contains(m)) {
            m.habitants.add(o);
        } else {
            throw new NotAvaliableException("You have no access to that apartment!");
        }
    }


    // Dodanie przedmioty do miejsca parkingowego
    public void addItem(ParkingSpot mp, Item p) throws NotAvaliableException, TooManyThingsException {
        if (p.placed){
            throw new NotAvaliableException("This item is already in another place!");
        } else if (mp.getFreeVolume() > p.volume ) {
            throw new TooManyThingsException("Remove some old items to insert a new item");
        }
    }


    private boolean rentingSpaceAvaliable() {
        return this.places.size() < 5;
    }

    private boolean hasApartment() {
        for (Place p : this.places) {
            if (p.getClass() == Apartment.class)
                return true;
        }
        return false;
    }
}

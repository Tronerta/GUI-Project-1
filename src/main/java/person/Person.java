package person;

import exceptions.*;
import helpers.DateHelper;
import objects.Item;
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

    public List<File> letters;
    public List<Place> places;
    private static int index = 0;
    public String id;

    public Person(String name, String surname, String PESEL, String address, LocalDate birthDate) {
        this.name = name;
        this.surname = surname;
        this.PESEL = PESEL;
        this.address = address;
        this.birthDate = birthDate;
        this.letters = new ArrayList<>();
        this.places = new ArrayList<>();
        this.id = "P-" + ++index;
    }

    public String toInfoString() {
        StringBuilder result = new StringBuilder("Information about " + this.toString() + "\n");
        result.append(" - ID: ").append(id).append("\n");
        result.append(" - PESEL: ").append(PESEL).append("\n");
        result.append(" - Address: ").append(address).append("\n");
        result.append(" - Birth date: ").append(birthDate).append("\n");
        result.append(" - Number of Files: ").append(letters.size()).append("\n \n");
        if (!places.isEmpty()) {
            result.append("Rented Places: \n");
            for (Place p : places) {
                result.append(p.toString()).append("\n");
            }
        }
        return result.toString();
    }

    public String toString() {
        return "[" + id + "] " + name + " " + surname + "\n";
    }

    // Rent a place
    public void rent(Place p) throws NotAvaliableException, TooManyThingsException, ProblematicTenantException {
        if (letters.size() >= 3){
            throw new ProblematicTenantException(getProblematicTenantMessage());
        }
        if ((p instanceof ParkingSpot) && this.getApartments().isEmpty()) {
            throw new NotAvaliableException("Rent an apartment first!");
        } else {
            if (this.rentingSpaceAvaliable()) {
                places.add(p);
                p.setTenant(this);
                p.startDate = DateHelper.randomDate();
                p.endDate = p.startDate.plusDays(30);
            } else {
                throw new TooManyThingsException("You have too much rented places");
            }
        }
    }

    // Manage people in apartments
    public void addPerson(Apartment m, Person o) throws NotAvaliableException {
        if (this.places.contains(m)) {
            m.habitants.add(o);
            System.out.println("You checked in " + o.name + " " + o.surname + " to " + m.id + "\n");
        } else {
            throw new NotAvaliableException("You have no access to that apartment!");
        }
    }

    public void removePerson(Apartment m, Person o) throws NotAvaliableException {
        if (this.places.contains(m)) {
            m.habitants.remove(o);
            System.out.println("You checked out " + o.name + " " + o.surname + " from " + m.id + "\n");
        } else {
            throw new NotAvaliableException("You have no access to that apartment!");
        }
    }

    // Add item to the parking spot
    public void addItem(ParkingSpot parking, Item p) throws NotAvaliableException, TooManyThingsException {
        if (p.placed) {
            throw new NotAvaliableException("This item is already in another place!");
        } else if (parking.getFreeVolume() < p.volume) {
            throw new TooManyThingsException("Remove some old items to insert a new item");
        } else {
            parking.items.add(p);
            p.placed = true;
        }
    }

    public void removeItem(ParkingSpot mp, Item p) throws NotAvaliableException {
        if (mp.items.contains(p)) {
            mp.items.remove(p);
            p.placed = false;
        } else {
            throw new NotAvaliableException("This item is not in that parking spot!");
        }
    }

    private boolean rentingSpaceAvaliable() {
        return this.places.size() < 5;
    }

    public List<Apartment> getApartments(){
        List<Apartment> apartments = new ArrayList<>();
        for (Place p : places) {
            if (p instanceof Apartment)
                apartments.add((Apartment) p);
        }
        return apartments;
    }

    public List<Place> getExpiredPlaces(){
        List<Place> expiredPlaces = new ArrayList<>();
        for (Place p : places){
            if (p.expired)
                expiredPlaces.add(p);
        }
        return expiredPlaces;
    }


    public List<Place> getParkingSpots(){
        List<Place> parkings = new ArrayList<>();
        for (Place p : places) {
            if (p instanceof ParkingSpot)
                parkings.add(p);
        }
        return parkings;
    }

    public boolean hasGuestsInApartment(){
        for (Apartment apartment : this.getApartments()){
            if (!apartment.habitants.isEmpty()){
                return true;
            }
        }
        return false;
    }

    public String getProblematicTenantMessage(){
        List<String> places = new ArrayList<>();
        StringBuilder list = new StringBuilder();
        for (File file : letters) {
            String[] arr = file.getName().split("_");
            if (arr[0].equals(id)){
                String[] arr2 = arr[1].split("-");
                list.append(arr2[0].equals("A") ? "Apartment " : "Parkings Spot ");
                list.append(arr2[1].split(".")[0]).append(", ");
            }
        }
        return "Osoba " + this.toString() + " posiadała już najem pomieszczeń: " + list.toString();
    }

    // Managing letters
    public File getLetterForPlace(Place place){
        if (letters.isEmpty())
            return null;
        for (File letter : letters){
            if (letter.getName().split("_")[1].split("\\.")[0].equals(place.id)){
                return letter;
            }
        }
        return null;
    }

    public void removeLetterForPlace(Place place){
        File letter = this.getLetterForPlace(place);
        if (letter != null){
            this.letters.remove(letter);
            letter.delete();
        }
    }
}

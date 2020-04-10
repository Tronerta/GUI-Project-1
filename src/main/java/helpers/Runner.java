package helpers;

import exceptions.NotAvaliableException;
import exceptions.ProblematicTenantException;
import exceptions.TooManyThingsException;
import objects.Item;
import person.Person;
import places.Apartment;
import places.Estate;
import places.ParkingSpot;
import places.Place;

import java.util.*;

public class Runner {
    Estate estate;
    Person currentPerson;

    public Runner(Estate estate) throws NotAvaliableException, TooManyThingsException, InterruptedException {
        this.estate = estate;
        start();
    }

    private void start() throws NotAvaliableException, TooManyThingsException, InterruptedException {
        this.currentPerson = selectPerson();
        System.out.println("Hello " + currentPerson.toString());
        try {
            runMenu();
        } catch (Exception e){
            System.out.println("Something went wrong...");
            if (this.currentPerson != null){
                runMenu();
            } else {
                start();
            }
        }
    }

    private Person selectPerson() throws NotAvaliableException, InterruptedException, TooManyThingsException {
        String greeting = "*********************************\n" +
                "* Welcome to the EstateManager! *\n" +
                "*********************************\n" +
                "ID | " + "   Person   \n" +
                "----------------------\n";
        return setObject(estate.people, greeting, "Please, choose which person you are: \n");
    }

    private void runMenu() throws NotAvaliableException, TooManyThingsException, InterruptedException {
        String menu = "Please, choose one of the options below: \n" +
                "| 1. Show your data \n" +
                "| 2. Show/Rent free places \n" +
                "| 3. Manage your apartments \n" +
                "| 4. Manage items and vehicles \n" +
                "| 5. Save all data to the file \n" +
                "| 6. Select another person \n" +
                "| 7. End program \n";
        switch (getMenuChoice(menu, 7)) {
            case 0:
                System.out.println(currentPerson.toInfoString());
                break;
            case 1:
                rentNewPlace();
                break;
            case 2:
                managePlaces();
                break;
            case 3:
                manageItems();
                break;
            case 4:
                estate.saveReport();
                break;
            case 5:
                start();
                break;
            case 6:
                System.exit(0);
                break;
            default:
                runMenu();
        }
    }


    // Places managing
    private void managePlaces() throws NotAvaliableException, InterruptedException, TooManyThingsException {
        String menu = "What do you want to do? (Type 0 to return to previous menu) \n" +
                "1. Check-in person to your apartment \n" +
                "2. Check-out person from your apartment \n" +
                "3. Renew place rent \n" +
                "4. Stop renting the place \n";
        switch (getMenuChoice(menu, 4) + 1) {
            case 1:
                checkIn();
                break;
            case 2:
                checkOut();
                break;
            case 3:
                renewRent();
                break;
            case 4:
                endRent();
                break;
        }
    }

    private void rentNewPlace() throws NotAvaliableException, TooManyThingsException, InterruptedException {
        Place currentPlace = setObject(estate.getFreePlaces(), "Free places: \n", "Choose which place you want to rent: (Type 0 to return)\n");
        try {
            currentPerson.rent(currentPlace);
        } catch (NotAvaliableException | TooManyThingsException | ProblematicTenantException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Congratulations! You successfully rented new place! ");
    }

    private void renewRent() throws NotAvaliableException, InterruptedException, TooManyThingsException {
        if (currentPerson.getExpiredPlaces().isEmpty()) {
            System.out.println("You have no expired places");
            managePlaces();
        } else {
            Place placeToRenew = setObject(currentPerson.getExpiredPlaces(), "Your expired places: \n", "Choose which place you want to renew retning: (Type 0 to return)\n");
            try {
                currentPerson.rent(placeToRenew);
                currentPerson.removeLetterForPlace(placeToRenew);
                System.out.println("Congratulations! You successfully renewed a rent for " + placeToRenew.id + "\n");
            } catch (NotAvaliableException | TooManyThingsException | ProblematicTenantException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void endRent() throws NotAvaliableException, InterruptedException, TooManyThingsException {
        if (currentPerson.places.isEmpty()) {
            System.out.println("You have no places");
            managePlaces();
        } else {
            Place placeToEnd = setObject(currentPerson.places, "Your places: \n", "Choose which place you want to end renting: (Type 0 to return)\n");
            placeToEnd.clean();
            currentPerson.removeLetterForPlace(placeToEnd);
            System.out.println("You ended renting " + placeToEnd.id + "\n");
        }
    }

    // Habitants managing
    private void checkIn() throws NotAvaliableException, InterruptedException, TooManyThingsException {
        List<Person> peopleWithoutCurrent = estate.people;
        peopleWithoutCurrent.remove(currentPerson);
        Apartment currentApartment = setObject(currentPerson.getApartments(), "Your Apartments: \n", "Choose an apartment where you want to check-in some person (Type 0 to return)\n");
        peopleWithoutCurrent.removeAll(currentApartment.habitants);
        Person personGuest = setObject(peopleWithoutCurrent, "People: \n", "Choose a person you want to check-in in " + currentApartment.id + ":\n");

        try {
            currentPerson.addPerson(currentApartment, personGuest);
        } catch (NotAvaliableException e) {
            System.out.println(e.getMessage());
        }
    }

    private void checkOut() throws NotAvaliableException, InterruptedException, TooManyThingsException {
        if (currentPerson.getApartments().isEmpty()) {
            System.out.println("You have no apartments");
            managePlaces();
        } else if (currentPerson.hasGuestsInApartment()) {
            Apartment currentApartment = setObject(currentPerson.getApartments(), "Your Apartments: \n", "Choose an apartment where you want to check-out person (Type 0 to return)\n");
            Person personGuest = setObject(currentApartment.habitants, "Your habitants: \n", "Choose a person you want to check out from " + currentApartment.id + ":\n");
            try {
                currentPerson.removePerson(currentApartment, personGuest);
            } catch (NotAvaliableException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("You have no people living in your apartments");
            managePlaces();
        }

    }

    // Items managing
    private void manageItems() throws NotAvaliableException, InterruptedException, TooManyThingsException {
        String menu = "What do you want to do? (Type 0 to return) \n 1. Add items to Parkings Spot \n 2. Remove items from Parking Spot \n";
        int id = getMenuChoice(menu, 2);
        if (id == 0) {
            placeItems();
        } else {
            removeItems();
        }
    }

    private void placeItems() throws NotAvaliableException, TooManyThingsException, InterruptedException {
        ParkingSpot currentParking = (ParkingSpot) setObject(currentPerson.getParkingSpots(), "Your parking spots: \n", "Please choose parking spot where you want to place an item");
        Item currentItem = setObject(estate.getFreeItems(), "Free items: \n", "Please choose an item you want to place: (Type 0 to return to previous menu) \n");

        try {
            currentPerson.addItem(currentParking, currentItem);
        } catch (NotAvaliableException | TooManyThingsException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("You successfully added " + currentItem.title + " to the " + currentParking.id);
    }

    private void removeItems() throws NotAvaliableException, TooManyThingsException, InterruptedException {
        ParkingSpot currentParking = (ParkingSpot) setObject(currentPerson.getParkingSpots(), "Your parking spots: \n", "Please choose Parking Spot that you want to remove item from: (Type 0 to return to previous menu)\n");
        Item currentItem = setObject(estate.getFreeItems(), "Items in " + currentParking.id + ":\n", "Please choose an item you want to remove \n");

        try {
            currentPerson.removeItem(currentParking, currentItem);
            System.out.println("You successfully removed " + currentItem.title + " from " + currentParking.id);
        } catch (NotAvaliableException e) {
            System.out.println(e.getMessage());
        }
    }

    // Setters & getters
    private <T> T setObject(List<T> objects, String header, String footer) throws NotAvaliableException, TooManyThingsException, InterruptedException {
        StringBuilder menu = new StringBuilder(header);
        if (objects.isEmpty()) {
            System.out.println("No objects to display...");
            runMenu();
        } else {
            for (int i = 1; i <= objects.size(); i++) {
                menu.append(i).append(". ").append(objects.get(i - 1).toString());
            }
            menu.append(footer);
        }
        int id = getMenuChoice(menu.toString(), objects.size());
        return objects.get(id);
    }

    private int getMenuChoice(String menu, int listSize){
        int id = 0;
        System.out.println(menu);
        try {
            Scanner in = new Scanner(System.in);
            id = in.nextInt();
        } catch (InputMismatchException e) {
            getMenuChoice(menu, listSize);
        }
        if (id < 0 || id > listSize)
            getMenuChoice(menu, listSize);
        return id - 1;
    }
}

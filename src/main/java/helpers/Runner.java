package helpers;

import exceptions.NotAvaliableException;
import exceptions.ProblematicTenantException;
import exceptions.TooManyThingsException;
import objects.Item;
import person.Person;
import places.Estate;
import places.ParkingSpot;
import places.Place;
import tasks.CheckStatusTask;
import tasks.DateTask;

import java.util.*;

public class Runner {
    Estate estate;
    Person currentPerson;
    String menu;

    public Runner(Estate estate) throws NotAvaliableException, TooManyThingsException, InterruptedException {
        this.estate = estate;
        menu = "Please, choose one of the options below: \n" +
                "| 1. Show your data \n" +
                "| 2. Show/Rent free places \n" +
                "| 3. Manage your apartments \n" +
                "| 4. Manage items and vehicles \n" +
                "| 5. Save all data to the file \n" +
                "| 6. Select another person \n" +
                "| 7. End program \n";
        // Run constant tasks
        new Timer().schedule(new DateTask(), 0, 5000);
        new Timer().schedule(new CheckStatusTask(estate), 0, 10000);
        start();
    }

    private void start() throws NotAvaliableException, TooManyThingsException, InterruptedException {
        this.currentPerson = selectPerson();
        System.out.println("Hello " + currentPerson.toString());
        while (true) {
            runMenu();
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
        switch (getMenuChoice(this.menu, 8)) {
            case 0:
                System.out.println(currentPerson.toInfoString());
                break;
            case 1:
                rentNewPlace();
                break;
            case 2:
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
        }
    }

    private int getMenuChoice(String menu, int listSize) throws NotAvaliableException, InterruptedException, TooManyThingsException {
        int id = 0;
        do {
            System.out.println("\n" + menu);
            try {
                Scanner in = new Scanner(System.in);
                id = in.nextInt();
            } catch (InputMismatchException e) {
                getMenuChoice(menu, listSize);
            }
        } while (id < 0 || id > listSize);
        if (id == 0){
            runMenu();
        }
        return id - 1;
    }

    private void rentNewPlace() throws NotAvaliableException, TooManyThingsException, InterruptedException {
        Place currentPlace = setObject(estate.getFreePlaces(), "Free places: \n", "Choose which place you want to rent: (Type 0 to return to previous menu)\n");
        try {
            currentPerson.rent(currentPlace);
            System.out.println("Congratulations! You successfully rented new place! ");
        } catch (NotAvaliableException | TooManyThingsException | ProblematicTenantException e) {
            System.out.println(e.getMessage());
        }
    }

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
        Item currentItem = setObject(estate.getFreeItems(), "Free items: \n", "Please choose an item you want to place: (Type 0 to return to previous menu) \n");
        ParkingSpot currentParking = (ParkingSpot) setObject(currentPerson.getParkingSpots(), "Your parking spots: \n", "Please choose parking spot where you want to place " + currentItem.title);

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

    // Setters
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
}

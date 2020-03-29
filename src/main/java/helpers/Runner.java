package helpers;

import exceptions.NotAvaliableException;
import exceptions.TooManyThingsException;
import objects.Item;
import person.Person;
import places.Estate;
import places.ParkingSpot;
import places.Place;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Runner {
    Estate estate;
    Person currentPerson;
    boolean end = false;
    String menu;

    public Runner(Estate estate) throws NotAvaliableException, TooManyThingsException {
        this.estate = estate;
        menu = "Please, choose one of the options below: \n" +
                "| 1. Show your data \n" +
                "| 2. Show free places to rent \n" +
                "| 3. Rent new place \n" +
                "| 4. Place items and vehicles \n" +
                "| 5. Remove items and vehicles \n" +
                "| 6. Save all data to the file \n" +
                "| 7. Select another person \n" +
                "| 8. End program \n";
        start();
    }

    private void start() throws NotAvaliableException, TooManyThingsException {
        this.currentPerson = selectPerson();
        System.out.println("Hello " + currentPerson.toSmallString());
        while (!end) {
            runMenu();
        }
    }

    private Person selectPerson() {
        StringBuilder greeting = new StringBuilder("*********************************\n");
        greeting.append("* Welcome to the EstateManager! *\n");
        greeting.append("*********************************\n");
        greeting.append("ID | ").append("   Person   \n");
        greeting.append("----------------------\n");

        for (int i = 1; i <= estate.people.size(); i++) {
            greeting.append(i).append(". ").append(estate.people.get(i - 1).toSmallString()).append("\n");
        }

        greeting.append("Please, choose which person you are: \n");

        return estate.people.get(getMenuChoice(greeting.toString(), estate.people.size()));
    }

    private void runMenu() throws NotAvaliableException, TooManyThingsException {
        switch (getMenuChoice(this.menu, 8)) {
            case 0:
                System.out.println(currentPerson);
                break;
            case 1:
                System.out.println(showFreePlaces());
                break;
            case 2:
                rentNewPlace();
                break;
            case 3:
                placeItems();
                break;
            case 4:
                removeItems();
                break;
            case 5:
                estate.saveReport();
                break;
            case 6:
                start();
                break;
            case 7:
                end = true;
                break;
        }
    }

    private int getMenuChoice(String menu, int listSize) {
        int id = 0;
        do {
            System.out.println("\n" + menu);
            try {
                Scanner in = new Scanner(System.in);
                id = in.nextInt();
            } catch (InputMismatchException e) {
                getMenuChoice(menu, listSize);
            }
        } while (id < 1 || id > listSize);

        return id - 1;
    }

    private String showFreePlaces() {
        List<Place> freePlaces = estate.getFreePlaces();
        if (freePlaces.isEmpty()) {
            System.out.println("No free places left for now...");
            return null;
        } else {
            StringBuilder result = new StringBuilder("Free Places: \n");
            for (int i = 1; i <= freePlaces.size(); i++) {
                result.append(i).append(". ").append(freePlaces.get(i - 1).toString());
            }
            return result.toString();
        }
    }

    private void rentNewPlace() throws NotAvaliableException, TooManyThingsException {
        Place currentPlace = setPlace(estate.getFreePlaces(), "Free places: \n", "Please choose a place you want to rent: ");
        try {
            currentPerson.rent(currentPlace);
        } catch (NotAvaliableException | TooManyThingsException e) {
            System.out.println(e.getMessage());
            runMenu();
        }
        System.out.println("Congratulations! You successfully rented new place! ");
    }

    private void placeItems() throws NotAvaliableException, TooManyThingsException {
        Item currentItem = setItem(estate.getFreeItems(), "Free items: \n", "Please choose an item you want to place \n");
        ParkingSpot currentParking = (ParkingSpot) setPlace(currentPerson.getParkingSpots(), "Your parking spots: \n", "Please choose parking spot where you want to place " + currentItem.title);

        try {
            currentPerson.addItem(currentParking, currentItem);
        } catch (NotAvaliableException | TooManyThingsException e) {
            System.out.println(e.getMessage());
            runMenu();
        }

        System.out.println("You successfully added " + currentItem.title + " to the " + currentParking.id);
    }

    private void removeItems() throws NotAvaliableException, TooManyThingsException {
        ParkingSpot currentParking = (ParkingSpot) setPlace(currentPerson.getParkingSpots(), "Your parking spots: \n", "Please choose Parking Spot that you want to remove item from: \n");
        Item currentItem = setItem(estate.getFreeItems(), "Items in " + currentParking.id + ":\n", "Please choose an item you want to remove \n");

        try {
            currentPerson.removeItem(currentParking, currentItem);
        } catch (NotAvaliableException e) {
            System.out.println(e.getMessage());
            runMenu();
        }

        System.out.println("You successfully removed " + currentItem.title + " from " + currentParking.id);
    }

    // Setters
    private Item setItem(List<Item> items, String header, String footer) throws NotAvaliableException, TooManyThingsException {
        StringBuilder menu = new StringBuilder(header);
        if (items.isEmpty()) {
            System.out.println("No items to display...");
            runMenu();
        } else {
            for (int i = 1; i <= items.size(); i++) {
                menu.append(i).append(". ").append(items.get(i - 1).toString());
            }
            menu.append(footer);
        }
        int id = getMenuChoice(menu.toString(), items.size());
        return items.get(id);
    }

    private Place setPlace(List<Place> places, String header, String footer) throws NotAvaliableException, TooManyThingsException {
        StringBuilder menu = new StringBuilder(header);
        if (places.isEmpty()){
            System.out.println("No places to display...");
            runMenu();
        } else {
            for (int i = 1; i <= places.size(); i++) {
                menu.append(i).append(". ").append(places.get(i - 1).toString());
            }
            menu.append(footer);
        }
        int id = getMenuChoice(menu.toString(), places.size());
        return places.get(id);
    }
}

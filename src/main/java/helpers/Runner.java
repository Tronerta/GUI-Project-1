package helpers;

import exceptions.NotAvaliableException;
import exceptions.TooManyThingsException;
import objects.Item;
import person.Person;
import places.Estate;
import places.ParkingSpot;
import places.Place;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Runner {
    Estate estate;
    Person currentPerson;
    boolean end = false;

    public Runner(Estate estate) throws NotAvaliableException, TooManyThingsException {
        this.estate = estate;
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
        greeting.append("ID | ").append("Name and Surname\n");
        greeting.append("----------------------\n");

        for (Person p : estate.people) {
            greeting.append(p.id).append(": ").append(p.name).append(" ").append(p.surname).append("\n");
        }

        greeting.append("Please, type an ID of person you are: \n");
        String id = "";
        while (estate.findPerson(id) == null) {
            System.out.println(greeting.toString());
            System.out.println("ID: ");
            Scanner in = new Scanner(System.in);
            id = in.nextLine();
        }
        return estate.findPerson(id);
    }

    private void runMenu() throws NotAvaliableException, TooManyThingsException {
        switch (getMenuChoice()) {
            case 1:
                System.out.println(currentPerson);
                break;
            case 2:
                estate.showFreePlaces();
                break;
            case 3:
                rentNewPlace();
                break;
            case 4:
                placeItems();
                break;
            case 7:
                start();
                break;
            case 8:
                end = true;
                break;
        }
    }

    private int getMenuChoice() {
        StringBuilder menu = new StringBuilder("Please, choose one of the options below: \n");
        menu.append("| 1. Show your data \n");
        menu.append("| 2. Show free places to rent \n");
        menu.append("| 3. Rent new place \n");
        menu.append("| 4. Place items and vehicles \n");
        menu.append("| 5. Remove items and vehicles \n");
        menu.append("| 6. Save all data to the file \n");
        menu.append("| 7. Select another person \n");
        menu.append("| 8. End program \n");

        int result = 0;
        do {
            System.out.println(menu);
            try {
                Scanner in = new Scanner(System.in);
                result = in.nextInt();
            } catch (InputMismatchException e) {
                getMenuChoice();
            }

        } while (result < 1 || result > 8);

        return result;
    }



    private void showFreeItems(){
        System.out.println("Free Items: ");
        for (Item i : estate.items) {
            if (!i.placed)
                System.out.println(i);
        }
    }

    private void rentNewPlace() throws NotAvaliableException, TooManyThingsException {
        estate.showFreePlaces();
        String id;
        do {
            System.out.println("Please type an ID of place you want to rent");
            Scanner in = new Scanner(System.in);
            id = in.nextLine();
        } while (estate.findPlace(id) == null);

        try {
            currentPerson.rent(estate.findPlace(id));
        } catch (NotAvaliableException | TooManyThingsException e) {
            System.out.println(e.getMessage());
            runMenu();
        }
        System.out.println("Congratulations! You successfully rented new place! ");
    }

    private void placeItems() throws NotAvaliableException, TooManyThingsException {
        showFreeItems();
        String itemId;
        do {
            System.out.println("Type an ID of item you want to place: ");
            Scanner in = new Scanner(System.in);
            itemId = in.nextLine();
        } while (estate.findItem(itemId) == null);
        Item currentItem = estate.findItem(itemId);

        System.out.println("Your Parking Spots: ");
        for (Place p : currentPerson.places){
            if (p instanceof ParkingSpot)
                System.out.println(p);
        }
        String parkingId;
        do {
            System.out.println("Type an ID of Parking Spot where you want to put " + currentItem.title + ": ");
            Scanner in = new Scanner(System.in);
            parkingId = in.nextLine();
        } while (estate.findParkingSpot(parkingId) == null && !currentPerson.places.contains(estate.findParkingSpot(parkingId)));
        ParkingSpot currentParking = estate.findParkingSpot(parkingId);
        try {
            currentPerson.addItem(currentParking, currentItem);
        } catch (NotAvaliableException | TooManyThingsException e) {
            System.out.println(e.getMessage());
            runMenu();
        }
        System.out.println("You successfully added " + currentItem.title + " to the " + currentParking.id);
    }
}

package helpers;

import exceptions.NotAvaliableException;
import exceptions.TooManyThingsException;
import person.Person;
import places.Apartment;
import places.Estate;
import places.ParkingSpot;
import places.Place;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Runner {
    List<Person> people;
    List<Apartment> apartments;
    List<ParkingSpot> parkings;
    Person currentPerson;
    boolean end = false;

    public Runner(List<Person> people, Estate estate) throws NotAvaliableException, TooManyThingsException {
        this.people = people;
        this.apartments = estate.apartments;
        this.parkings = estate.parkings;
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

        for (Person p : people) {
            greeting.append(p.id).append(": ").append(p.name).append(" ").append(p.surname).append("\n");
        }

        greeting.append("Please, type an ID of person you are: \n");
        String id = "";
        while (findPerson(id) == null) {
            System.out.println(greeting.toString());
            System.out.println("ID: ");
            Scanner in = new Scanner(System.in);
            id = in.nextLine();
        }
        return findPerson(id);
    }

    private void runMenu() throws NotAvaliableException, TooManyThingsException {
        switch (getMenuChoice()) {
            case 1:
                System.out.println(currentPerson);
                break;
            case 2:
                showFreePlaces();
                break;
            case 3:
                rentNewPlace();
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

    private void showFreePlaces() {
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

    private void rentNewPlace() throws NotAvaliableException, TooManyThingsException {
        showFreePlaces();
        System.out.println("Please type an ID of place you want to rent");
        String id;
        do {
            Scanner in = new Scanner(System.in);
            id = in.nextLine();
        } while (findPlace(id) == null);

        try {
            currentPerson.rent(findPlace(id));
        } catch (NotAvaliableException | TooManyThingsException e) {
            System.out.println(e.getMessage());
            rentNewPlace();
        }

        System.out.println("Congratulations! You successfully rented new place! ");

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
        for (ParkingSpot ps : parkings) {
            if (ps.id.equals(id))
                return ps;
        }
        return null;
    }
}

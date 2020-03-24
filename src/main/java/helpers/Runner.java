package helpers;

import person.Person;
import places.Apartment;
import places.Estate;
import places.ParkingSpot;

import java.util.List;
import java.util.Scanner;

public class Runner {
    List<Person> people;
    List<Apartment> apartments;
    List<ParkingSpot> parkings;
    Person currentPerson;

    public Runner(List<Person> people, Estate estate) {
        this.people = people;
        this.apartments = estate.apartments;
        this.parkings = estate.parkings;
        start();
    }

    private void start() {
        this.currentPerson = selectPerson();
        switch (getMenuChoice()) {
            case 1:
                System.out.println(currentPerson);
                break;
            case 2:

        }
    }

    private Person selectPerson() {
        StringBuilder greeting = new StringBuilder("*********************************\n");
        greeting.append("* Welcome to the EstateManager! *\n");
        greeting.append("*********************************\n");
        greeting.append("Please, type an ID of person you are: \n");
        greeting.append("ID | ").append("Name and Surname\n");
        greeting.append("----------------------\n");

        for (Person p : people) {
            greeting.append(p.id).append(": ").append(p.name).append(" ").append(p.surname).append("\n");
        }

        String id = "";
        while (findPerson(id) == null) {
            System.out.println(greeting.toString());
            System.out.println("ID: ");
            Scanner in = new Scanner(System.in);
            id = in.nextLine();
        }

        return findPerson(id);
    }

    private int getMenuChoice() {
        StringBuilder menu = new StringBuilder("Please, choose one of the options below: \n");
        menu.append("1. Show your data \n");
        menu.append("2. Show free places to rent \n");
        menu.append("3. Rent new place \n");
        menu.append("4. Show info about your place \n");
        menu.append("5. Place items and vehicles \n");
        menu.append("6. Remove items and vehicles \n");
        menu.append("7. Save all data to the file \n");
        menu.append("8. Select another person \n");
        menu.append("0. End program \n");

        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

    private void showFreePlaces() {
        System.out.println("Free Apartments: ");
        for (Apartment a : apartments) {
            if (a.isAvaliable()){
                System.out.println(a);
            }
        }

        System.out.println("Free Parking Spots: ");
        for (ParkingSpot ps : parkings) {
            if (ps.isAvaliable()){
                System.out.println(ps);
            }
        }
    }


    public Person findPerson(String id) {
        for (Person p : people) {
            if (p.id.equals(id))
                return p;
        }
        return null;
    }
}

import exceptions.NotAvaliableException;
import exceptions.TooManyThingsException;
import helpers.DateHelper;
import objects.Item;
import objects.Vehicle;
import person.Person;
import places.Apartment;
import places.Estate;
import places.ParkingSpot;
import places.Place;
import vehicles.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws NotAvaliableException, TooManyThingsException {

        // Initial setup
        List<Apartment> apartments = new ArrayList<>();
        List<ParkingSpot> parkings = new ArrayList<>();
        List<Person> people = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            String[] names = {"John", "Tomasz", "Jacek", "Adrian", "Anna"};
            String[] surnames = {"Skrzekut", "Riddle", "Tomaszewski", "Jasinski", "Nowak"};
            String[] PESELs = {"12345678901", "09876543212", "2345678901", "98765432101", "34567890123"};

            apartments.add(new Apartment(Math.random() * (200 - 10 + 1) + 10));
            people.add(new Person(names[i], surnames[i], PESELs[i], "Warszawa, ul. Nowaka 3/9", DateHelper.randomDate()));
            parkings.add(new ParkingSpot(Math.random() * (300 - 10 + 1) + 10));
        }

//        Estate estate1 = new Estate(places);

        for (int i = 0; i < 3; i++) {
            people.get(i).rent(apartments.get(i));
        }

        people.get(0).rent(parkings.get(0));
        people.get(1).rent(parkings.get(1));

        Item box = new Item(15.29, "Box");
        Item ball = new Item(1, 2.1, 3.2, "Ball");
        Boat boat1 = new Boat(67.13, "Galaxy", 180.32, "FastEngine V3.0", "Large Boat", 0);
        Motorcycle bike1 = new Motorcycle(89.11, "Golf 3", 89.50, "Turbo Diesel V2", "Hachback", 9.3);

        people.get(0).addItem(parkings.get(0), box);
        people.get(1).addItem(parkings.get(1), boat1);

        run(people);
    }

    public static void run(List<Person> people) {
        Scanner in = new Scanner(System.in);


        StringBuilder greeting = new StringBuilder("*********************************\n");
        greeting.append("* Welcome to the EstateManager! *\n");
        greeting.append("*********************************\n");
        greeting.append("Please, type an ID of person you are: \n");
        greeting.append("ID | ").append("Name and Surname\n");
        greeting.append("----------------------\n");

        for (Person p : people){
            greeting.append(p.id).append(": ").append(p.name).append(" ").append(p.surname).append("\n");
        }

        String currentPerson = in.nextLine();

        String menu = "Please, choose one of the options below: \n";
        menu += "1. Show your data \n";
        menu += "2. Show free places to rent \n";
        menu += "3. Save all data to the file \n";
        menu += "4. Select another person \n";
        menu += "0. End program \n";


        boolean end = false;
        while (!end) {
            System.out.println(menu);
        }


        System.out.println(greeting.toString());
    }
}

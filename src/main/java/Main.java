import exceptions.NotAvaliableException;
import exceptions.ProblematicTenantException;
import exceptions.TooManyThingsException;
import helpers.DateHelper;
import helpers.Runner;
import objects.Item;
import person.Person;
import places.Apartment;
import places.Estate;
import places.ParkingSpot;
import objects.vehicles.*;
import tasks.CheckStatusTask;
import tasks.DateTask;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;


public class Main {

    public static void main(String[] args) throws NotAvaliableException, TooManyThingsException, InterruptedException, ProblematicTenantException {

        // Initial setup
        List<Apartment> apartments = new ArrayList<>();
        List<ParkingSpot> parkings = new ArrayList<>();
        List<Person> people = new ArrayList<>();
        List<Item> items = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            String[] names = {"Slawomir", "Michal", "Michal", "Peter", "Anna"};
            String[] surnames = {"Danczak", "Skrzekut", "Tomaszewski", "Werner", "Nowak"};
            String[] PESELs = {"12345678901", "09876543212", "2345678901", "98765432101", "34567890123"};

            apartments.add(new Apartment(Math.random() * (200 - 80 + 1) + 80));
            people.add(new Person(names[i], surnames[i], PESELs[i], "Warszawa, ul. Nowaka 3/9", DateHelper.randomDate()));
            parkings.add(new ParkingSpot(Math.random() * (300 - 80 + 1) + 80));
        }

        for (int i = 0; i < 3; i++) {
            people.get(i).rent(apartments.get(i));
        }

        people.get(0).rent(parkings.get(0));
        people.get(1).rent(parkings.get(1));

        Item box = new Item(5.29, "Box");
        Item ball = new Item(1, 2.1, 3.2, "Ball");
        Boat boat1 = new Boat(25.13, "Galaxy", 180.32, "FastEngine V3.0", "Large Boat", 0);
        Motorcycle bike1 = new Motorcycle(21.11, "Golf 3", 89.50, "Turbo Diesel V2", "Hatchback", 9.3);
        CityCar car1 = new CityCar(75.23, "Toyota SV", 123.5, "VVV Extreme", "Sedan", 9.3);
        Amphibian amphibian1 = new Amphibian(126.13, "Amphibian 1", 87.23, "Land-Water x3", "Amphibia", 34.5);

        items.add(box);
        items.add(ball);
        items.add(boat1);
        items.add(bike1);
        items.add(car1);
        items.add(amphibian1);

        people.get(0).addItem(parkings.get(0), box);
        people.get(1).addItem(parkings.get(1), boat1);

        Estate estate = new Estate(apartments, parkings, items, people);

        // Run
        deleteAllFiles("letters");
        deleteAllFiles("reports");
        new Timer().schedule(new DateTask(), 0, 5000);
        new Timer().schedule(new CheckStatusTask(estate), 0, 10000);
        new Runner(estate);
    }

    // Delete all files in folder
    private static void deleteAllFiles(String path){
        File directory = new File(path);
        File[] files = directory.listFiles();
        if (files == null)
            return;
        for (File file : files){
            if (!file.delete()){
                System.out.println("Failed to delete " + file);
            }
        }
    }
}

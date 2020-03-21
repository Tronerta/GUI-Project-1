import helpers.DateHelper;
import person.Person;
import places.Apartment;
import places.Estate;
import places.ParkingSpot;
import places.Place;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Main {

    static void greeting(){
        System.out.println("*************************");
        System.out.println("Welcome to EstateManager!");
        System.out.println("*************************");
    }

    public static void main(String[] args) {
//        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
//        DateHelper Dates;
//        try {
//            LocalDate todaysDate = ft.parse("2020-03-20");
//            Dates = new DateHelper(todaysDate);
//        } catch (ParseException ex) {
//            ex.printStackTrace();
//        }

        List<Place> places = new ArrayList<>();
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            String[] names = {"John", "Tomek", "Jacek", "Adrian", "Anna"};
            String[] surnames = {"Black", "Riddle", "Tomaszewski", "Jasinski", "Nowak"};
            String[] PESELs = {"12345678901", "09876543212", "2345678901", "98765432101", "34567890123"};

            places.add(new Apartment(Math.random() * (200 - 10 + 1) + 10));
            places.add(new ParkingSpot(Math.random() * (100 - 10 + 1) + 10));
            people.add(new Person(names[i], surnames[i], PESELs[i], "Warszawa, ul. Nowaka 3/9", DateHelper.randomDate()));
        }

        Estate estate1 = new Estate(places);
    }
}

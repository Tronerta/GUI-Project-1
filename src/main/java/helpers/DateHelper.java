package helpers;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class DateHelper {
    public static LocalDate todayDate = LocalDate.of(2020, 3, 20);

    public static LocalDate randomDate() {
        long min = todayDate.minusDays(20).toEpochDay();
        long max = todayDate.plusDays(20).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(min, max);
        return LocalDate.ofEpochDay(randomDay);
    }
}

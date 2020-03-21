package helpers;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class DateHelper {
    public static LocalDate todayDate = LocalDate.of(2020, 03, 20);
    private static LocalDate twentyDaysBefore = todayDate.minusDays(20);
    private static LocalDate twentyDaysAfter = todayDate.plusDays(20);

    public static LocalDate randomDate() {
        long min = twentyDaysBefore.toEpochDay();
        long max = twentyDaysAfter.toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(min, max);
        return LocalDate.ofEpochDay(randomDay);
    }
}

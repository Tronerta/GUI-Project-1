package objects;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Object {
    public double volume;

    public Object (double volume) {
        this.volume = round(volume, 2);
    }

    public Object(double a, double b, double c) {
        this.volume = round((a * b * c), 2);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}

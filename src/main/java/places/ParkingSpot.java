package places;

import objects.Item;
import java.util.ArrayList;
import java.util.List;

public class ParkingSpot extends Place {
    private static int index = 0;
    public List<Item> items = new ArrayList<Item>();

    public ParkingSpot(double volume) {
        super(volume);
        super.id = "MP" + ++index;
    }

    public double getFreeVolume(){
        double sum = 0;
        for (Item p : items){
            sum += p.volume;
        }
        return this.volume - sum;
    }
}

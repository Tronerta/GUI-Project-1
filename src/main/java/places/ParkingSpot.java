package places;

import objects.Item;
import java.util.ArrayList;
import java.util.List;

public class ParkingSpot extends Place {
    private static int index = 0;
    public List<Item> items = new ArrayList<Item>();

    public ParkingSpot(double volume) {
        super(volume);
        super.id = "PS-" + ++index;
    }



    public double getFreeVolume(){
        double sum = 0;
        for (Item p : items){
            sum += p.volume;
        }
        return this.volume - sum;
    }

    public String toString(){
        StringBuilder result = new StringBuilder(super.toString());
        if (!items.isEmpty()){
            for (Item i : this.items){
                result.append("Placed items: \n");
                result.append(i.toString()).append("\n");
            }
        }
        return result.toString();
    }
}

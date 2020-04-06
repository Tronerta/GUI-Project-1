package places;

import objects.Item;
import objects.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class ParkingSpot extends Place {
    private static int index = 0;
    public List<Item> items = new ArrayList<>();

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
            result.append(" * Placed items in ").append(id).append(":\n");
            for (Item i : this.items){
                result.append(i.toString()).append("\n");
            }
        }
        return result.toString();
    }

    public void clean(){
        Vehicle vehicle = (Vehicle) getVehicle();
        if (vehicle == null){
            super.clean();
            for (Item item : items)
                item.placed = false;
            items.clear();
        } else {
            vehicle.placed = false;
            items.remove(vehicle);
            super.endDate = super.endDate.plusDays(60);
        }
    }

    private Item getVehicle(){
        for (Item item : items){
            if (item instanceof Vehicle)
                return item;
        }
        return null;
    }
}

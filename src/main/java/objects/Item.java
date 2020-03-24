package objects;

public class Item extends Object {
    public String title;
    public boolean placed = false;
    public String id;

    public Item(double volume, String title) {
        super(volume);
        this.title = title;
    }

    public Item(double a, double b, double c, String title) {
        super(a, b, c);
        this.title = title;
    }
}

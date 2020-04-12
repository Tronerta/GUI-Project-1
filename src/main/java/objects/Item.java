package objects;

public class Item extends Object {
    public String title;
    public boolean placed = false;
    public String id;
    private static int index = 0;

    public Item(double volume, String title) {
        super(volume);
        this.title = title;
        this.id = "I-" + ++index;
    }

    public Item(double a, double b, double c, String title) {
        super(a, b, c);
        this.title = title;
        this.id = "I-" + ++index;
    }

    public String toString(){
        return " Item ID: " + id + "\n" +
                "  - Placed: " + (placed ? "Yes" : "No") + "\n" +
                "  - Title: " + title + "\n" +
                "  - Volume: " + volume + "\n";
    }
}

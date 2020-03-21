package objects;

public class MiejsceParkingowe extends Pomieszczenie {
    private static int index = 0;

    public MiejsceParkingowe(double volume) {
        super(volume);
        super.id = "MP" + ++index;
    }
}

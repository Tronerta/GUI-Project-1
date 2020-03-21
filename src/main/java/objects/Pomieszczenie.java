package objects;

public class Pomieszczenie {
    double volume;
    Osoba najemca;
    public String id;

    public Pomieszczenie(double volume) {
        this.volume = volume;
    }

    public boolean isAvaliable() {
        return this.najemca == null;
    }

    public void setNajemca(Osoba osoba) {
        this.najemca = osoba;
    }

}

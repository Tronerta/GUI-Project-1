package objects;

import exceptions.NotAvaliableException;

import java.util.ArrayList;
import java.util.List;


public class Osoba {
    public String imie;
    public String nazwisko;
    public String PESEL;
    public String adres;
    public String data_urodzenia;

    Letter[] letters;
    List<Pomieszczenie> pomieszczenia = new ArrayList<Pomieszczenie>();
    private static int index = 0;
    public String id;

    public Osoba(String imie, String nazwisko, String PESEL, String adres, String data_urodzenia) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.PESEL = PESEL;
        this.adres = adres;
        this.data_urodzenia = data_urodzenia;
        this.id = "O" + ++index;
    }

    // Wynajecie pomieszczenia
    private void rent(Pomieszczenie p) throws NotAvaliableException {
        if ((p.getClass() == MiejsceParkingowe.class) && !this.hasMieszkanie()) {
            throw new NotAvaliableException("Najpierw musisz wynajac mieszkanie!");
        } else {
            if (p.isAvaliable()) {
                if (this.rentingSpaceAvaliable()) {
                    pomieszczenia.add(p);
                    p.setNajemca(this);
                } else {
                    throw new NotAvaliableException("Masz za dużo wynajetych pomiszczeń");
                }
            } else {
                throw new NotAvaliableException("To mieszkanie jest juz wynajete");
            }
        }
    }

    // Dodanie mieszkanca do mieszkania
    private void addMieszkaniec(Mieszkanie m, Osoba o) throws NotAvaliableException {
        if (this.pomieszczenia.contains(m)) {
            m.mieszkancy.add(o);
        } else {
            throw new NotAvaliableException("Nie masz uprawnien do tego mieszkania!");
        }
    }


    private boolean rentingSpaceAvaliable() {
        return this.pomieszczenia.size() < 5;
    }

    private boolean hasMieszkanie() {
        for (Pomieszczenie p : this.pomieszczenia) {
            if (p.getClass() == Mieszkanie.class)
                return true;
        }
        return false;
    }
}

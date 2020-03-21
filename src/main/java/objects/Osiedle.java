package objects;

import java.util.ArrayList;
import java.util.List;

public class Osiedle {
    private List<Pomieszczenie> pomieszczenia = new ArrayList<Pomieszczenie>();

    public Osiedle(List<Pomieszczenie> pomieszczenia) {
        this.pomieszczenia = pomieszczenia;
    }

    public Pomieszczenie find(String id) {
        for (Pomieszczenie p : this.pomieszczenia) {
            if (p.id.equals(id))
                return p;
        }
        return null;
    }
}

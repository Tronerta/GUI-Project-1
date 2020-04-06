package tasks;

import helpers.DateHelper;
import places.Estate;
import places.Place;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.TimerTask;

public class CheckStatusTask extends TimerTask {
    Estate estate;

    public CheckStatusTask(Estate estate) {
        this.estate = estate;
    }

    @Override
    public void run() {
        for (Place p : estate.places) {
            if (!p.isAvaliable()) {
                if (p.tenant.getLetterForPlace(p) == null) {
                    if (DateHelper.todayDate.isAfter(p.endDate)) {
                        try {
                            File file = new File("letters/" + p.tenant.id + "_" + p.id + ".txt");
                            Files.write(file.toPath(), Collections.singleton("Your rent for " + p.id + " is ended!"));
                            p.tenant.letters.add(file);
                            System.out.println("Letter for " + p.tenant.id + " was added!");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    if (p.tenant.getLetterForPlace(p).lastModified() > DateHelper.todayDate.minusDays(30).toEpochDay())
                        p.clean();
                }
            }
        }
    }
}

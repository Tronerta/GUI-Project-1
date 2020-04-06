package tasks;

import helpers.DateHelper;

import java.util.TimerTask;

public class DateTask extends TimerTask {
    @Override
    public void run() {
        DateHelper.todayDate = DateHelper.todayDate.plusDays(1);
    }
}

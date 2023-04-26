package dmit2015.timers;

import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.Timer;

@Singleton
@Startup
public class AutomaticTimers {
    @Schedule(minute = "*", hour = "*", info = "every minute of every hour", persistent = false)
    public void everySecondTimer(Timer timer) {
        System.out.println("Hello from Timer");
    }
}

package main.java.abstraction.assignment_problems;

/**
 * Week 7 - Problem 1: AlarmClock implementing Ringable.
 */
public class AlarmClock implements Ringable {
    private final String time;

    public AlarmClock(String time) {
        this.time = time;
    }

    @Override
    public String ring() {
        return "Alarm ringing for " + time;
    }
}

package com.example.finalxcmeettracker;

public class Athlete implements Comparable<Athlete> {
    private final String name;
    private final Team team;
    private int bibNumber;
    private long time;
    private int placement;
    private final boolean finished;

    public Athlete(String name, Team team) {
        this.name = name;
        this.team = team;
        finished = false;
    }

    public String getName() {
        return this.name;
    }

    public Team getTeam() {
        return this.team;
    }

    public boolean setBibNumber(int number) {
        if (number > 0) {
            this.bibNumber = number;
            return true;
        }
        return false;
    }

    public long getTime() {
        if (finished) {
            return this.time;
        }
        throw new IllegalCallerException("Athlete has not finished.");
    }

    public int getPlacement() {
        if (finished) {
            return this.placement;
        }
        throw new IllegalCallerException("Athlete has not finished.");
    }

    @Override
    public int compareTo(Athlete o) {
        return (int) (o.time - this.time);
    }
}
package com.example.finalxcmeettracker;

/**
 * A class for storing information about an athlete
 */
public class Athlete implements Comparable<Athlete> {
    private final String name;
    private final Team team;
    private int bibNumber;
    private long time;
    private int placement;
    private boolean finished;

    /**
     * Creates a new athlete with the desired name and team parameters
     * @param name the Athlete's name, String
     * @param team the Athlete's team, Team
     */
    public Athlete(String name, Team team) {
        this.name = name;
        this.team = team;
        finished = false;
    }

    /**
     * Sets the Athlete's bib number
     * @param number The Athlete's bib number, must be greater than 0
     * @return true if the Athlete's bib number was set, false otherwise
     */
    public boolean setBibNumber(int number) {
        if (number > 0) {
            this.bibNumber = number;
            return true;
        }
        return false;
    }

    /**
     * Set's the athlete's placement, i.e., in what position they finished the race
     * @param place The Athlete's placement, must be greater than 0
     * @return true if the Athlete's placement was set, false otherwise
     */
    public boolean setPlacement(int place) {
        if (place > 0) {
            this.placement = place;
            return true;
        }
        return false;
    }

    /**
     * Finishes the Athlete's race, sets time and finished
     * @param time The Athlete's time in milliseconds, must be non-negative
     * @return true if the Athlete's time and finished are set, false otherwise
     */
    public boolean finish(long time) {
        if (time >= 0) {
            this.time = time;
            this.finished = true;
            return true;
        }
        return false;
    }

    /**
     * Gets the Athlete's name
     * @return String, the Athlete's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the Athlete's Team
     * @return Team, the Athlete's Team
     */
    public Team getTeam() {
        return this.team;
    }

    /**
     * Gets the Athlete's time, if finished
     * @return long, the Athlete's time if finished, otherwise throws IllegalCallerException
     */
    public long getTime() {
        if (finished) {
            return this.time;
        }
        throw new IllegalCallerException("Athlete has not finished.");
    }

    /**
     * Gets the Athlete's placement, if finished
     * @return int, the Athlete's placement if finished, otherwise throws IllegalCallerException
     */
    public int getPlacement() {
        if (finished) {
            return this.placement;
        }
        throw new IllegalCallerException("Athlete has not finished.");
    }

    /**
     * Gets the Athlete's bib number
     * @return int, the Athlete's bib number
     */
    public int getBibNumber() {
        return this.bibNumber;
    }

    /**
     * Gets whether the Athlete is finished
     * @return true if the Athlete is finished, false otherwise
     */
    public boolean getFinished() {
        return this.finished;
    }
    @Override
    public int compareTo(Athlete o) {
        return (int) (o.time - this.time);
    }
}

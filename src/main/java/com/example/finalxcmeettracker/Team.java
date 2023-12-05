package com.example.finalxcmeettracker;

import java.util.ArrayList;

/**
 * Stores information about a Team in an XC Meet
 */
public class Team implements Comparable<Team> {
    private final ArrayList<Athlete> athletes;
    private final String name;
    private int teamScore;
    private int teamPlacement;

    /**
     * Creates a new Team with the desired parameters
     * @param teamName the name of the Team
     */
    public Team(String teamName) {
        this.name = teamName;
        this.athletes = new ArrayList<>();
    }

    /**
     * Sets the Team's score
     * @param score the Team's score, must be greater than or equal to 1
     * @return true if the Team's score is set, false otherwise
     */
    public boolean setScore(int score) {
        if (score >= 1) {
            teamScore = score;
            return true;
        }
        return false;
    }

    /**
     * Sets the Team's placement, i.e., the position that they finished in
     * @param place the Team's placement
     * @return true if the Team's placement was set, false otherwise
     */
    public boolean setPlacement(int place) {
        if (place > 0) {
            this.teamPlacement = place;
            return true;
        }
        return false;
    }

    /**
     * Adds a new Athlete to the Team
     * @param a the Athlete to add, must be nonnull
     * @return true if the Athlete was added to the Team, false otherwise
     */
    public boolean addAthlete(Athlete a) {
        if (a != null) {
            this.athletes.add(a);
            InformationController.meet.addAthlete();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gets the Athletes on this Team
     * @return the Athletes on the Team
     */
    public ArrayList<Athlete> getAthletes() {
        return athletes;
    }

    /**
     * Gets the Team's score
     * @return the Team's score
     */
    public int getTeamScore() {
        return this.teamScore;
    }

    /**
     * Gets the Team's placement
     * @return the Team's placement
     */
    public int getTeamPlacement() {
        return this.teamPlacement;
    }

    /**
     * Gets the Team's name
     * @return the Team's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Computes and returns the average time of the Athletes in the Team
     * Returns +inf if there are no Athletes in the Team
     * @return the average time of Athletes in the Team, in milliseconds since the start of the meet
     */
    public long getAverageTime() {
        int n = athletes.size();
        if (n == 0) {
            return Integer.MAX_VALUE;
        }
        long sum = 0;
        for (Athlete athlete : athletes) {
            if (athlete.getFinished()) {
                sum += athlete.getTime();
            }
        }
        return sum / n;
    }

    @Override
    public int compareTo(Team o) {
        return o.teamPlacement - this.teamPlacement;
    }
}

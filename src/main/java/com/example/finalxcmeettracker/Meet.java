package com.example.finalxcmeettracker;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Stores information about a Meet, including Athletes, Team, name, and description
 */
public class Meet {
    private String name;
    private String description;
    private final ArrayList<Team> teams;
    private final HashMap<Integer, Athlete> bibNumbers;
    private final Heap<Team> teamHeap;
    private int numAthletes;

    /**
     * Creates a new Meet with no name or description
     */
    public Meet() {
        this.teams = new ArrayList<>();
        this.bibNumbers = new HashMap<>();
        this.teamHeap = new Heap<>(10);
    }

    /**
     * Creates a new meet with a name
     * @param name String, the name of the meet, must be non-empty and nonnull
     */
    public Meet(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        }
        this.teams = new ArrayList<>();
        this.bibNumbers = new HashMap<>();
        this.teamHeap = new Heap<>(10);
    }

    /**
     * Creates a new meet with a name and a description
     * @param name String, the name of the meet, must be non-empty and nonnull
     * @param description String, the name of the meet, must be non-empty and nonnull
     */
    public Meet(String name, String description) {
        if (description != null && !description.isEmpty()) {
            this.description = description;
        }
        if (name != null && !name.isEmpty()) {
            this.name = name;
        }
        this.teams = new ArrayList<>();
        this.bibNumbers = new HashMap<>();
        this.teamHeap = new Heap<>(10);
    }

    /**
     * Gets the team in index i
     * @param i The index of teams to return, must be between 0 and teams.size() - 1, inclusive
     * @return The Team at teams.get(i), if i is out of range, return null
     */
    public Team getTeam(int i) {
        if (0 <= i && i < teams.size()) {
            return teams.get(i);
        }
        return null;
    }

    /**
     * Gets an Athlete in the Meet by bib number and removes it from the Meet
     * @param number The Athlete's bib number, must be a valid bib number from 1 to numAthletes, inclusive
     * @return The Athlete with bibNumber number, if none exists return null
     */
    public Athlete getAndRemoveAthlete(int number) {
        Athlete athlete = bibNumbers.getOrDefault(number, null);
        bibNumbers.remove(number, athlete);
        return athlete;
    }

    /**
     * Gets the teamHeap corresponding to this Meet
     * @return Heap<Team> the teamHeap
     */
    public Heap<Team> getTeamHeap() {
        return teamHeap;
    }

    /**
     * Gets the number of Athletes in the Meet
     * @return int, the number of Athletes
     */
    public int getNumAthletes() {
        return numAthletes;
    }

    /**
     * Adds a new team to the Meet
     * @param t Team, the team to add, must be nonnull
     * @return true if t was added to the Meet, false otherwise
     */
    public boolean addTeam(Team t) {
        if (t != null) {
            this.teams.add(t);
            return true;
        }
        return false;
    }

    /**
     * Adds an Athlete to the meet, actual Athlete objects are stored in the Team objects
     */
    public void addAthlete() {
        this.numAthletes++;
    }

    /**
     * Calculates the team score for every Team in the Meet based on Athlete placements
     * Right now, this is not how actual XC meets are scored, we can easily change this later.
     */
    public void calculateTeamScores() {
        // Possibly change this to only score teams that had more than 5 runners
        for (Team team : teams) {
            int score = 0;
            for (Athlete athlete : team.getAthletes()) {
                if (athlete.getFinished()) {
                    score += athlete.getPlacement();
                }
            }
            team.setScore(score);
            teamHeap.push(team);
        }
    }

    /**
     * Generates the bib numbers for all athletes in the meet.
     * Bib numbers are in [1, numAthletes]
     */
    public void generateBibNumbers() {
        int index = 1;
        for (Team t : teams) {
            for (Athlete a : t.getAthletes()) {
                a.setBibNumber(index);
                bibNumbers.put(index, a);
                index++;
            }
        }
        numAthletes = index - 1;
    }
}

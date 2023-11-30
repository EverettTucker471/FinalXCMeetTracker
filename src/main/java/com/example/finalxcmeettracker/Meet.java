package com.example.finalxcmeettracker;

import java.util.ArrayList;
import java.util.HashMap;

public class Meet {
    private String name;
    private String description;
    private ArrayList<Team> teams;
    private HashMap<Integer, Athlete> bibNumbers;
    private Heap<Team> teamHeap;
    private int num_athletes;

    public Meet() {
        this.teams = new ArrayList<>();
        this.bibNumbers = new HashMap<>();
        this.teamHeap = new Heap<>(10);
    }

    public Meet(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        }
        this.teams = new ArrayList<>();
        this.bibNumbers = new HashMap<>();
    }

    public Meet(String name, String description) {
        if (description != null && !description.isEmpty()) {
            this.description = description;
        }
        if (name != null && !name.isEmpty()) {
            this.name = name;
        }
        this.teams = new ArrayList<>();
        this.bibNumbers = new HashMap<>();
    }

    public boolean addTeam(Team t) {
        if (t != null) {
            this.teams.add(t);
            return true;
        }
        return false;
    }

    public void generateBibNumbers() {
        int index = 1;
        for (Team t : teams) {
            for (Athlete a : t.getAthletes()) {
                a.setBibNumber(index);
                bibNumbers.put(index, a);
                index++;
            }
        }
        num_athletes = index - 1;
    }

    public Team getTeam(int index) {
        if (0 <= index && index < teams.size()) {
            return teams.get(index);
        }
        return null;
    }

    public Athlete getAndRemoveAthlete(int number) {
        Athlete athlete = bibNumbers.getOrDefault(number, null);
        bibNumbers.remove(number, athlete);
        return athlete;
    }

    public void calculateTeamScores() {
        // Possibly change this to only score teams that had more than 5 runners
        for (Team team : teams) {
            int score = 0;
            for (Athlete athlete : team.getAthletes()) {
                score += athlete.getPlacement();
            }
            team.setScore(score);
            teamHeap.push(team);
        }
    }

    public Heap<Team> getTeamHeap() {
        return teamHeap;
    }

    public int getNumAthletes() {
        return num_athletes;
    }
}

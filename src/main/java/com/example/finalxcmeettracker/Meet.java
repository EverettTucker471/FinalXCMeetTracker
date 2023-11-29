package com.example.finalxcmeettracker;

import java.util.ArrayList;
import java.util.HashMap;

public class Meet {
    private String name;
    private String description;
    private ArrayList<Team> teams;
    private HashMap<Integer, Athlete> bibNumbers;
    private int num_athletes;

    public Meet() {
        this.teams = new ArrayList<>();
        this.bibNumbers = new HashMap<>();
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

    public int getNumAthletes() {
        return num_athletes;
    }
}

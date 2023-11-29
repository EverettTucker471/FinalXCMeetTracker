package com.example.finalxcmeettracker;

import java.util.ArrayList;

public class Team {
    private ArrayList<Athlete> athletes;
    private final String name;
    private int teamScore;
    private int teamPlacement;

    public Team(String teamName) {
        this.name = teamName;
        this.athletes = new ArrayList<>();
    }

    public boolean addAthlete(Athlete a) {
        if (a != null) {
            this.athletes.add(a);
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Athlete> getAthletes() {
        return athletes;
    }

    public int getTeamScore() {
        return this.teamScore;
    }

    public int getTeamPlacement() {
        return this.teamPlacement;
    }

    public String getName() {
        return this.name;
    }
}

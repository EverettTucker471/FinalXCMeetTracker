package com.example.finalxcmeettracker;

import java.util.ArrayList;

public class Team implements Comparable<Team> {
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
            InformationController.meet.addAthlete();
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

    public boolean setScore(int score) {
        if (score >= 1) {
            teamScore = score;
            return true;
        }
        return false;
    }

    public boolean setPlacement(int place) {
        if (place > 0) {
            this.teamPlacement = place;
            return true;
        }
        return false;
    }

    public long getAverageTime() {
        int n = athletes.size();
        if (n == 0) {
            return 1000000;
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

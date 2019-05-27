package nl.semtech.gamelibrary.model;

import java.util.ArrayList;

public class Genre {
    private String name;
    private ArrayList<Franchise> franchises;

    public Genre(String name, ArrayList<Franchise> franchises) {
        this.name = name;
        this.franchises = franchises;
    }

    public String getName() {
        return name;
    }

    public int getFranchisesCount() {
        return franchises.size();
    }

    public ArrayList<Franchise> getFranchises() {
        return franchises;
    }
}

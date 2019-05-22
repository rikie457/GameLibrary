package nl.semtech.gamelibrary.model;

import java.util.ArrayList;

public class Franchise {
    private String name;
    public ArrayList<Game> games;

    public Franchise(String name, ArrayList<Game> games) {
        this.name = name;
        this.games = games;
    }

    public String getName() {
        return name;
    }

    public int getGamesCount() {
        return games.size();
    }
}

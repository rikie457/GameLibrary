package nl.semtech.gamelibrary.model;

import java.util.ArrayList;

public class Game {
    private String name;
    private double price;
    private ArrayList<Genre> genres;

    public Game(String name, double price, ArrayList<Genre> genres) {
        this.name = name;
        this.price = price;
        this.genres = genres;
    }
}

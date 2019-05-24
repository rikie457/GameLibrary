package nl.semtech.gamelibrary.model;

import javax.validation.constraints.Size;
import java.util.ArrayList;

public class Franchise {
    @Size(min = 2, max = 20)
    private String name;

    private ArrayList<Game> games;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addGameToList(Game game) {
        games.add(game);
    }

}

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

    public void addGameToFranchise(Game game) {
        if (this.games == null) {
            this.games = new ArrayList<>();
        }
        games.add(game);
    }

    public int getGamesCount() {
        return games.size();
    }

}

package nl.semtech.gamelibrary.model;

import javax.validation.constraints.Size;
import java.util.ArrayList;

public class Franchise {
    @Size(min = 2, max = 20)
    private String name;

    private int id;

    private Genre genre;

    private ArrayList<Game> games;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addGameToFranchise(Game game) {
        if (this.games == null) {
            this.games = new ArrayList<>();
        }
        games.add(game);
    }

    public int getGamesCount() {
        if (this.games == null) {
            return 0;
        }
        return games.size();
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Genre getGenre() {
        return genre;
    }


}

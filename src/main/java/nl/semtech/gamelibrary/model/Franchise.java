package nl.semtech.gamelibrary.model;

import javax.validation.constraints.Size;
import java.util.ArrayList;

public class Franchise {
    @Size(min = 2, max = 30)
    private String name;

    private int id;

    private Genre genre;

    private int genreid;

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
        this.games.add(game);
    }

    public void deleteGameFromFranchise(Game game) {
        if (this.games != null) {
            this.games.remove(game);
        }
    }

    public int getGamesCount() {
        if (this.games == null) {
            return 0;
        }
        return games.size();
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getGenreid() {
        return genreid;
    }

    public void setGenreid(int genreid) {
        this.genreid = genreid;
    }

    @Override
    public String toString() {
        return "Franchise{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", genre=" + genre.toString() +
                ", games=" + getGamesCount() +
                '}';

    }

    public ArrayList<Game> getGames() {
        return this.games;
    }

    public String getLink(){
        return "franchise?name=" + name;
    }
}

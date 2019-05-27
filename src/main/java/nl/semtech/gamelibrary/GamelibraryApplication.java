package nl.semtech.gamelibrary;

import nl.semtech.gamelibrary.model.Franchise;
import nl.semtech.gamelibrary.model.Game;
import nl.semtech.gamelibrary.model.Genre;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class GamelibraryApplication {

    static ArrayList<Genre> genres;

    static ArrayList<Game> games;

    static ArrayList<Franchise> franchises;

    public static void main(String[] args) {
        SpringApplication.run(GamelibraryApplication.class, args);
        genres = new ArrayList<>();
        games = new ArrayList<>();
        franchises = new ArrayList<>();

        games.add(new Game("Semih", 1));
        Franchise franchise1 = new Franchise("Call of duty", new ArrayList<>());
        Franchise franchise2 = new Franchise("Hello Kitty", new ArrayList<>());
        franchises.add(franchise1);
        franchises.add(franchise2);
        genres.add(new Genre("FPS", franchises));
        genres.add(new Genre("Puzzle", new ArrayList<>()));
        genres.add(new Genre("Indie", new ArrayList<>()));
        genres.add(new Genre("Simulation", new ArrayList<>()));
        genres.add(new Genre("Adventure", new ArrayList<>()));
        genres.add(new Genre("Creative", new ArrayList<>()));
    }

    public static Genre findGenreByName(String name){
        for (Genre genre : genres){
            if (genre.getName().equals(name)){
                return genre;
            }
        }
        return null;
    }
}

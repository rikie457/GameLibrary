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

        Franchise franchise = new Franchise();
        franchise.setName("Call of Duty");

        Game game = new Game();
        game.setName("Call of Duty: Modern Warfare");
        game.setPrice(30.99);

        Game game1 = new Game();
        game1.setName("Call of Duty: Black Ops 1");
        game1.setPrice(59.99);

        Genre fps = new Genre();
        fps.setName("FPS");

        franchise.addGameToFranchise(game);
        franchise.addGameToFranchise(game1);
        fps.addFranchiseToGenre(franchise);

        franchises.add(franchise);
        genres.add(fps);
        games.add(game);
        games.add(game1);
    }

    public static Genre findGenreByName(String name) {
        for (Genre genre : genres) {
            if (genre.getName().equals(name)) {
                return genre;
            }
        }
        return null;
    }

    public static Franchise findFranchiseByName(String name) {
        for (Franchise franchise : franchises) {
            if (franchise.getName().equals(name)) {
                return franchise;
            }
        }
        return null;
    }

    public static Game findGameByName(String name) {
        for (Game game : games) {
            if (game.getName().equals(name)) {
                return game;
            }
        }
        return null;
    }
}

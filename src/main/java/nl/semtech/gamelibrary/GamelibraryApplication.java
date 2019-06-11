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

        Genre genre = new Genre();
        genre.setName("FPS");

        Genre genre1 = new Genre();
        genre1.setName("Action");

        Genre genre2 = new Genre();
        genre2.setName("Puzzle");

        Genre genre3 = new Genre();
        genre3.setName("Drama");

        Genre genre4 = new Genre();
        genre4.setName("Adventure");

        Genre genre5 = new Genre();
        genre5.setName("Simulation");

        Genre genre6 = new Genre();
        genre6.setName("Management");

        franchise.addGameToFranchise(game);
        franchise.addGameToFranchise(game1);
        genre.addFranchiseToGenre(franchise);
        genre1.addFranchiseToGenre(franchise);
        genre2.addFranchiseToGenre(franchise);
        genre3.addFranchiseToGenre(franchise);
        genre4.addFranchiseToGenre(franchise);
        genre5.addFranchiseToGenre(franchise);
        genre6.addFranchiseToGenre(franchise);

        franchises.add(franchise);
        genres.add(genre);
        genres.add(genre1);
        genres.add(genre2);
        genres.add(genre3);
        genres.add(genre4);
        genres.add(genre5);
        genres.add(genre6);
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

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
        franchise.setName("Call of duty");

        Game game = new Game();
        game.setName("test");
        game.setPrice(0);

        Game game1 = new Game();
        game1.setName("test1");
        game1.setPrice(1);

        Genre fps = new Genre();
        fps.setName("FPS");

        franchise.addGameToFranchise(game);
        franchise.addGameToFranchise(game1);
        fps.addFranchiseToGenre(franchise);

        franchises.add(franchise);
        genres.add(fps);
        game.setId(games.size() + 1);
        game.setFranchise(franchise);
        games.add(game);
        games.add(game1);

    }

    public static Genre findGenreById(int id) {
        for (Genre genre : genres) {
            if (genre.getId() == id) {
                return genre;
            }
        }
        return null;
    }

    public static Franchise findFranchiseById(int id) {
        for (Franchise franchise : franchises) {
            if (franchise.getId() == id) {
                return franchise;
            }
        }
        return null;
    }

    public static Game findGameById(int id) {
        for (Game game : games) {
            if (game.getId() == id) {
                return game;
            }
        }
        return null;
    }
}

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

        Franchise franchise1 = new Franchise();
        franchise1.setName("Hello kitty");

        Game game = new Game();
        game.setName("test");
        game.setPrice(0);

        Game game1 = new Game();
        game1.setName("test1");
        game1.setPrice(1);

        Genre fps = new Genre();
        fps.setName("FPS");

        game.setFranchise(franchise);
        game1.setFranchise(franchise1);

        franchise.addGameToFranchise(game);
        franchise1.addGameToFranchise(game1);
        franchise.setGenre(fps);
        franchise1.setGenre(fps);
        fps.addFranchiseToGenre(franchise);
        fps.addFranchiseToGenre(franchise1);

        franchises.add(franchise);
        franchise.setId(franchises.size());

        franchises.add(franchise1);
        franchise1.setId(franchises.size());

        genres.add(fps);
        fps.setId(genres.size());

        games.add(game);
        game.setId(games.size());

        games.add(game1);
        game1.setId(games.size());

        game.setFranchise(franchise);
        game.setFranchiseId(franchise.getId());
        game1.setFranchise(franchise1);
        game1.setFranchiseId(franchise1.getId());

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

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

        franchises.add(franchise);
        genres.add(fps);

        games.add(game);
        games.add(game1);

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

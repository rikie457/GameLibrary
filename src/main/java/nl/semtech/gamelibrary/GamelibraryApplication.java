package nl.semtech.gamelibrary;

import nl.semtech.gamelibrary.model.Franchise;
import nl.semtech.gamelibrary.model.Game;
import nl.semtech.gamelibrary.model.Genre;
import nl.semtech.gamelibrary.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class GamelibraryApplication {


    static ArrayList<User> users;

    public static void main(String[] args) {
        SpringApplication.run(GamelibraryApplication.class, args);

        users = new ArrayList<>();


        ArrayList<Genre> genres = new ArrayList<>();
        ArrayList<Game> games = new ArrayList<>();
        ArrayList<Franchise> franchises = new ArrayList<>();


        Franchise nonefr = new Franchise();
        nonefr.setName("No franchise");

        Franchise franchise1 = new Franchise();
        franchise1.setName("Hello kitty");

        Game game = new Game();
        game.setName("Call of Duty: Modern Warfare");
        game.setPrice(30.99);

        Game game1 = new Game();
        game1.setName("Call of Duty: Black Ops 1");
        game1.setPrice(59.99);

        Genre none = new Genre();
        none.setName("No genre");

        Genre adventure = new Genre();
        adventure.setName("Adventure");

        franchises.add(nonefr);
        none.setId(franchises.size());

        franchises.add(franchise1);
        franchise1.setId(franchises.size());

        genres.add(none);
        none.setId(genres.size());

        genres.add(adventure);
        adventure.setId(genres.size());

        games.add(game);
        game.setId(games.size());

        games.add(game1);
        game1.setId(games.size());

        game.setFranchise(nonefr);
        game.setFranchiseId(nonefr.getId());
        game1.setFranchise(franchise1);
        game1.setFranchiseId(franchise1.getId());
        nonefr.addGameToFranchise(game);
        franchise1.addGameToFranchise(game1);
        nonefr.setGenre(none);
        nonefr.setGenreid(none.getId());
        franchise1.setGenre(adventure);
        franchise1.setGenreid(adventure.getId());
        none.addFranchiseToGenre(nonefr);
        adventure.addFranchiseToGenre(franchise1);

        User user = new User();
        user.setUsername("test");
        user.setPassword("123");
        user.setName("Tycho");
        user.setGenres(genres);
        user.setFranchises(franchises);
        user.setGames(games);
        users.add(user);
        user.setId(users.size());


    }

    public static boolean checkUsernameAndPassword(String username, String password) {
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }

        }
        return false;
    }

    public static User getUserByUsernameAndPassword(String username, String password) {
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }

        }
        return null;
    }

    public static User getUserById(int id) {
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getId() == id) {
                return user;
            }

        }
        return null;
    }

}


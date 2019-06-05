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

        game.setFranchise(nonefr);
        game1.setFranchise(franchise1);

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


    }

    public static Genre findGenreById(int id) {
        for (Genre genre : genres) {
            if (genre.getId() == id) {
                return genre;
            }
        }
        return null;
    }


    public static void updateGenre(int id, Genre newgenre) {
        Genre genre = findGenreById(id);
        genre.setName(newgenre.getName());
    }

    public static void deleteGenre(int id) {
        Genre genre = findGenreById(id);
        if (genres.contains(genre)) {
            genres.remove(genre);

            for (int i = 0; i < franchises.size(); i++) {
                if (franchises.get(i).getGenreid() == id) {
                    franchises.get(i).setGenre(genres.get(0));
                }
            }
        }

    }

    public static Franchise findFranchiseById(int id) {
        for (Franchise franchise : franchises) {
            if (franchise.getId() == id) {
                     return franchise;
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

    public static void updateFranchise(int id, Franchise newfranchise, int oldgenreid) {
        Franchise franchise = findFranchiseById(id);
        Genre genre = findGenreById(newfranchise.getGenreid());
        Genre oldgenre = findGenreById(oldgenreid);
        if (genre != oldgenre) {
            oldgenre.deleteFranchiseFromGenre(findFranchiseById(id));
            franchise.setGenre(genre);
            genre.addFranchiseToGenre(findFranchiseById(id));
        }
        franchise.setName(newfranchise.getName());
        franchise.setGenreid(newfranchise.getGenreid());
    }

    public static void deleteFranchise(int id) {
        Franchise franchise = findFranchiseById(id);
        if (franchises.contains(franchise)) {
            franchises.remove(franchise);
            if (franchise.getGenre() != null) {
                franchise.getGenre().deleteFranchiseFromGenre(franchise);
            }
            for (int i = 0; i < games.size(); i++) {
                if (games.get(i).getFranchiseId() == id) {
                    games.get(i).setFranchise(franchises.get(0));
                }
            }
        }

    }

    public static Game findGameById(int id) {
        for (Game game : games) {
            if (game.getId() == id) {
              return game;
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


    public static void updateGame(int id, Game newgame, int oldfranchiseid) {
        Game game = findGameById(id);
        Franchise franchise = findFranchiseById(newgame.getFranchiseId());
        Franchise oldfranchise = findFranchiseById(oldfranchiseid);
        if (franchise != oldfranchise) {
            oldfranchise.deleteGameFromFranchise(findGameById(id));
            game.setFranchise(franchise);
            franchise.addGameToFranchise(findGameById(id));
        }
        game.setName(newgame.getName());
        game.setPrice(newgame.getPrice());
        game.setFranchiseId(newgame.getFranchiseId());
    }


    public static void deleteGame(int id) {
        Game game = findGameById(id);
        if (games.contains(game)) {
            games.remove(game);
            if (game.getFranchise() != null) {
                game.getFranchise().deleteGameFromFranchise(game);
            }
        }
    }
}


package nl.semtech.gamelibrary.model;

import javax.validation.constraints.Size;
import java.util.ArrayList;

public class User {
    private ArrayList<Franchise> franchises = new ArrayList<>();
    private ArrayList<Genre> genres = new ArrayList<>();
    private ArrayList<Game> games = new ArrayList<>();

    @Size(min = 2, max = 20)
    private String username;

    @Size(min = 2, max = 20)
    private String password;

    @Size(min = 2, max = 20)
    private String name;

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<Franchise> getFranchises() {
        return franchises;
    }

    public void setFranchises(ArrayList<Franchise> franchises) {
        this.franchises = franchises;
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<Genre> genres) {
        this.genres = genres;
    }

    public Genre findGenreById(int id) {
        for (Genre genre : genres) {
            if (genre.getId() == id) {
                return genre;
            }
        }
        return null;
    }

    public Genre findGenreByName(String name) {
        for (Genre genre : genres) {
            if (genre.getName().equals(name)) {
                return genre;
            }
        }
        return null;
    }


    public void updateGenre(int id, Genre newgenre) {
        Genre genre = findGenreById(id);
        genre.setName(newgenre.getName());
    }

    public void deleteGenre(int id) {
        Genre genre = findGenreById(id);
        for (Franchise franchise : franchises) {
            if (franchise.getGenre() == genre) {
                Genre replacegenre = findGenreById(1);
                franchise.setGenre(replacegenre);
                franchise.setGenreid(replacegenre.getId());
                replacegenre.getFranchises().add(franchise);
            }
        }
        genres.remove(genre);
    }

    public Franchise findFranchiseById(int id) {
        for (Franchise franchise : franchises) {
            if (franchise.getId() == id) {
                return franchise;
            }
        }
        return null;
    }

    public Franchise findFranchiseByName(String name) {
        for (Franchise franchise : franchises) {
            if (franchise.getName().equals(name)) {
                return franchise;
            }
        }
        return null;
    }

    public void updateFranchise(int id, Franchise newfranchise, int oldgenreid) {
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

    public void deleteFranchise(int id) {
        Franchise franchise = findFranchiseById(id);
        for (Game game : games) {
            if (game.getFranchise() == franchise) {
                Franchise replacefranchise = findFranchiseById(1);
                game.setFranchise(replacefranchise);
                game.setFranchiseId(replacefranchise.getId());
                replacefranchise.getGames().add(game);
                if (franchise.getGenre() != null) {
                    franchise.getGenre().deleteFranchiseFromGenre(franchise);
                }

            }
        }
        franchises.remove(franchise);
    }


    public Game findGameById(int id) {
        for (Game game : games) {
            if (game.getId() == id) {
                return game;
            }

        }
        return null;
    }

    public Game findGameByName(String name) {
        for (Game game : games) {
            if (game.getName().equals(name)) {
                return game;
            }
        }
        return null;
    }


    public void updateGame(int id, Game newgame, int oldfranchiseid) {
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


    public void deleteGame(int id) {
        Game game = findGameById(id);
        if (games.contains(game)) {
            games.remove(game);
            if (game.getFranchise() != null) {
                game.getFranchise().deleteGameFromFranchise(game);
            }
        }
    }

    public ArrayList<Object> searchAll(String s) {
        ArrayList<Object> results = new ArrayList<>();
        s = s.toLowerCase();
        results.addAll(findGamesByName(s));
        results.addAll(findFranchisesByName(s));
        results.addAll(findGenresByName(s));
        if (results.size() != 0) {
            return results;
        }
        return null;
    }

    private ArrayList<Game> findGamesByName(String s) {
        ArrayList<Game> names = new ArrayList<>();
        for (Game game : games) {
            if (game.getName().toLowerCase().contains(s)) {
                names.add(game);
            }
        }
        return names;
    }

    private ArrayList<Franchise> findFranchisesByName(String s) {
        ArrayList<Franchise> names = new ArrayList<>();
        for (Franchise franchise : franchises) {
            if (franchise.getName().toLowerCase().contains(s)) {
                names.add(franchise);
            }
        }
        return names;
    }

    private ArrayList<Genre> findGenresByName(String s) {
        ArrayList<Genre> names = new ArrayList<>();
        for (Genre genre : genres) {
            if (genre.getName().toLowerCase().contains(s)) {
                names.add(genre);
            }
        }
        return names;
    }

    @Override
    public String toString() {
        return "User{" +
                "franchises=" + franchises +
                ", genres=" + genres +
                ", games=" + games +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}

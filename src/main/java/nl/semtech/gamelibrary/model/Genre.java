package nl.semtech.gamelibrary.model;

import javax.validation.constraints.Size;
import java.util.ArrayList;

public class Genre {
    @Size(min = 2, max = 20)
    private String name;

    private ArrayList<Franchise> franchises;


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addFranchiseToGenre(Franchise franchise){
        if(this.franchises == null){
            this.franchises = new ArrayList<>();
        }
        this.franchises.add(franchise);
    }

    public int getFranchisesCount() {
        return franchises.size();
    }

    public ArrayList<Franchise> getFranchises() {
        return franchises;
    }
}

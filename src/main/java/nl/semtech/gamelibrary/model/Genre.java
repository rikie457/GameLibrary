package nl.semtech.gamelibrary.model;

import javax.validation.constraints.Size;
import java.util.ArrayList;

public class Genre {
    @Size(min = 2, max = 20)
    private String name;

    private ArrayList<Franchise> franchises;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

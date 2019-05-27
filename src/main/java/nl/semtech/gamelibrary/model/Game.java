package nl.semtech.gamelibrary.model;

import javax.validation.constraints.Size;

public class Game {

    @Size(min = 2, max = 20)
    private String name;

    private double price;

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

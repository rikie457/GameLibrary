package nl.semtech.gamelibrary.model;

import javax.validation.constraints.Size;

public class Game {

    @Size(min = 2, max = 30)
    private String name;

    private int id;

    private double price;

    private Franchise franchise;

    private int franchiseid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Franchise getFranchise() {
        return franchise;
    }

    public void setFranchise(Franchise franchise) {
        this.franchise = franchise;
    }

    public int getFranchiseId() {
        return franchiseid;
    }

    public void setFranchiseId(int franchiseid) {
        this.franchiseid = franchiseid;
    }

    @Override
    public String toString() {
        return "Game{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", price=" + price +
                ", franchise=" + franchise.toString() +
                ", franchiseid=" + franchiseid +
                '}';
    }

    public String getLink(){
        return "game?name=" + name;
    }
}

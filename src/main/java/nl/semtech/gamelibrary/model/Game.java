package nl.semtech.gamelibrary.model;

        import javax.validation.constraints.Size;

public class Game {

    @Size(min = 2, max = 20)
    private String name;

    private int id;

    private double price;

    private Franchise franchise;

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
}

package mate.academy.internetshop3.model;

public class Item {

    private Long id;
    private String name;
    private Double price;

    public Item(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public Item(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id; }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public String toString() {
        return "Item name: " + getName()
                + ", Item id " + getId();
    }
}

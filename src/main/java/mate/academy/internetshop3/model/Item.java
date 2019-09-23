package mate.academy.internetshop3.model;

import mate.academy.internetshop3.generator.ItemIdGenerator;

public class Item {

    private Long id;
    private String name;
    private Double price;

    public Item(String name, Double price) {
        this.name = name;
        this.id = ItemIdGenerator.getGeneratedId();
        this.price = price;
    }

    public Long getId() {
        return id;
    }

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
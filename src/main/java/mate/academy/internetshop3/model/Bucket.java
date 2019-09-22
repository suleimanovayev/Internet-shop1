package mate.academy.internetshop3.model;

import java.util.ArrayList;
import java.util.List;
import mate.academy.internetshop3.generator.BucketIdGenerator;

public class Bucket {
    private Long id;
    private Long userId;
    private List<Item> items;

    public Bucket(Long userId) {
        this.userId = userId;
        items = new ArrayList<>();
        this.id = BucketIdGenerator.getIdGenerator();
    }

    public Long getId() {
        return id;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}


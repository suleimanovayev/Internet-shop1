package mate.academy.internetshop3.model;

import java.util.ArrayList;
import java.util.List;

public class Bucket {
    private Long id;
    private Long userId;
    private List<Item> items;

    public Bucket(Long bucketId) {
        this.userId = userId;
        items = new ArrayList<>();
        this.id = bucketId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

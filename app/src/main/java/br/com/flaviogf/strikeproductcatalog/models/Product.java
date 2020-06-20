package br.com.flaviogf.strikeproductcatalog.models;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
    private final UUID id;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final String color;
    private final String category;

    public Product(UUID id, String name, String description, BigDecimal price, String color, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.color = color;
        this.category = category;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getColor() {
        return color;
    }

    public String getCategory() {
        return category;
    }
}

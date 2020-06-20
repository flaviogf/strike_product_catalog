package br.com.flaviogf.strikeproductcatalog.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import br.com.flaviogf.strikeproductcatalog.infrastructure.Maybe;

public class Product {
    private final UUID id;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final String category;
    private final List<Image> images;

    public Product(UUID id, String name, String description, BigDecimal price, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.images = new ArrayList<>();
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

    public String getCategory() {
        return category;
    }

    public Maybe<Image> getPrincipalImage() {
        try {
            Image image = images.get(0);
            return Maybe.of(image);
        } catch (IndexOutOfBoundsException ex) {
            return Maybe.empty();
        }
    }

    public List<Image> getImages() {
        return images;
    }

    public void addImage(Image... images) {
        this.images.addAll(Arrays.asList(images));
    }
}

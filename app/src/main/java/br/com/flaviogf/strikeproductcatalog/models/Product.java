package br.com.flaviogf.strikeproductcatalog.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import br.com.flaviogf.strikeproductcatalog.infrastructure.Maybe;
import br.com.flaviogf.strikeproductcatalog.infrastructure.Result;

@Entity
public class Product {
    @PrimaryKey
    @NonNull
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private String category;
    private final List<Image> images;

    public Product(@NonNull UUID id, String name, String description, BigDecimal price, String category, List<Image> images) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.images = images;
    }

    @NonNull
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

    public List<Image> getImages() {
        return images;
    }

    public Maybe<Image> getPrincipalImage() {
        try {
            Image image = images.get(0);
            return Maybe.of(image);
        } catch (IndexOutOfBoundsException ex) {
            return Maybe.empty();
        }
    }

    public Maybe<Image> getImage(UUID imageId) {
        for (Image image : images) {
            if (image.getId().equals(imageId)) {
                return Maybe.of(image);
            }
        }

        return Maybe.empty();
    }

    public Result<Void> remove(Image image) {
        if (images.size() <= 1) {
            return Result.fail("Product must be least one image");
        }

        images.remove(image);

        return Result.ok();
    }
}

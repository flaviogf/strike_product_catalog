package br.com.flaviogf.strikeproductcatalog.repositories;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import br.com.flaviogf.strikeproductcatalog.infrastructure.Result;
import br.com.flaviogf.strikeproductcatalog.models.Product;

public class MemoryProductRepository implements ProductRepository {
    @Override
    public Result<List<Product>> fetch() {
        List<Product> products = Arrays.asList(
                new Product(UUID.randomUUID(), "Adidas Alpha", "Size: 41", new BigDecimal("499.99"), "Black", "Shoes"),
                new Product(UUID.randomUUID(), "Adidas Neo", "Size: 41", new BigDecimal("499.99"), "Black", "Shoes")
        );

        Result<List<Product>> result = Result.ok(products);

        return result;
    }
}

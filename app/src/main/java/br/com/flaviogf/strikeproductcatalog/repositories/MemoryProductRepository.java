package br.com.flaviogf.strikeproductcatalog.repositories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import br.com.flaviogf.strikeproductcatalog.infrastructure.Result;
import br.com.flaviogf.strikeproductcatalog.models.Product;

public class MemoryProductRepository implements ProductRepository {
    private final static Map<UUID, Product> products = new HashMap<>();

    @Override
    public Result<Void> save(Product product) {
        products.put(product.getId(), product);

        return Result.ok();
    }

    @Override
    public Result<List<Product>> fetch() {
        List<Product> products = new ArrayList<>(MemoryProductRepository.products.values());

        Result<List<Product>> result = Result.ok(Collections.unmodifiableList(products));

        return result;
    }

    @Override
    public Result<Product> fetchOne(UUID id) {
        if (!products.containsKey(id)) {
            return Result.fail("Product does not exist");
        }

        Product product = products.get(id);

        return Result.ok(product);
    }
}

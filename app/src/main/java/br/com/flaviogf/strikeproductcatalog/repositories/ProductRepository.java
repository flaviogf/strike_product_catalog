package br.com.flaviogf.strikeproductcatalog.repositories;

import java.util.List;
import java.util.UUID;

import br.com.flaviogf.strikeproductcatalog.infrastructure.Result;
import br.com.flaviogf.strikeproductcatalog.models.Product;

public interface ProductRepository {
    Result<Void> save(Product product);

    Result<List<Product>> fetch();

    Result<Product> fetchOne(UUID id);
}

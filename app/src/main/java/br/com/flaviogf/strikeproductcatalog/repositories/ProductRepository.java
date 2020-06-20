package br.com.flaviogf.strikeproductcatalog.repositories;

import java.util.List;

import br.com.flaviogf.strikeproductcatalog.infrastructure.Result;
import br.com.flaviogf.strikeproductcatalog.models.Product;

public interface ProductRepository {
    Result<List<Product>> fetch();
}

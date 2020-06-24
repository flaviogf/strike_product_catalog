package br.com.flaviogf.strikeproductcatalog.repositories;

import java.util.List;
import java.util.UUID;

import br.com.flaviogf.strikeproductcatalog.database.ProductDao;
import br.com.flaviogf.strikeproductcatalog.infrastructure.Result;
import br.com.flaviogf.strikeproductcatalog.models.Product;

public class RoomProductRepository implements ProductRepository {
    private final ProductDao productDao;

    public RoomProductRepository(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Result<Void> save(Product product) {
        productDao.save(product);

        return Result.ok();
    }

    @Override
    public Result<List<Product>> fetch() {
        List<Product> products = productDao.findAll();

        return Result.ok(products);
    }

    @Override
    public Result<Product> fetchOne(UUID id) {
        Product product = productDao.findOne(id);

        if (product == null) {
            return Result.fail("Product does not exist");
        }

        return Result.ok(product);
    }

    @Override
    public Result<Void> remove(Product product) {
        productDao.delete(product);

        return Result.ok();
    }
}

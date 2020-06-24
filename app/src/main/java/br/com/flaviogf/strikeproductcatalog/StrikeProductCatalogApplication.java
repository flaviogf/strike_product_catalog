package br.com.flaviogf.strikeproductcatalog;

import android.app.Application;

import androidx.room.Room;

import br.com.flaviogf.strikeproductcatalog.database.ProductDao;
import br.com.flaviogf.strikeproductcatalog.database.StrikeProductCatalogDatabase;
import br.com.flaviogf.strikeproductcatalog.repositories.ProductRepository;
import br.com.flaviogf.strikeproductcatalog.repositories.RoomProductRepository;

public class StrikeProductCatalogApplication extends Application {
    public ProductRepository productRepository() {
        StrikeProductCatalogDatabase database = Room.databaseBuilder(this, StrikeProductCatalogDatabase.class, "strike_product_catalog.db").allowMainThreadQueries().build();

        ProductDao productDao = database.productDao();

        return new RoomProductRepository(productDao);
    }
}

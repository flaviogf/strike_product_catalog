package br.com.flaviogf.strikeproductcatalog;

import android.app.Application;

import java.math.BigDecimal;
import java.util.UUID;

import br.com.flaviogf.strikeproductcatalog.models.Image;
import br.com.flaviogf.strikeproductcatalog.models.Product;
import br.com.flaviogf.strikeproductcatalog.repositories.MemoryProductRepository;
import br.com.flaviogf.strikeproductcatalog.repositories.ProductRepository;

public class StrikeProductCatalogApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Product alpha = new Product(UUID.randomUUID(), "Adidas Alpha", "Size: 41 Color: Black", new BigDecimal("499.99"), "Shoes");
        alpha.addImage(new Image(UUID.randomUUID(), "alpha", "https://assets.adidas.com/images/h_840,f_auto,q_auto:sensitive,fl_lossy/d5997cf97dbd497ab14fa9fc0170f82f_9366/Tenis_Alphaboost_Branco_G28581_01_standard.jpg", "jpg"));

        Product neo = new Product(UUID.randomUUID(), "Adidas Neo", "Size: 41 Color: Black", new BigDecimal("499.99"), "Shoes");
        neo.addImage(new Image(UUID.randomUUID(), "alpha", "https://assets.adidas.com/images/h_840,f_auto,q_auto:sensitive,fl_lossy/770bdf735b3f4a458755a80b0119549b_9366/Tenis_VS_Advantage_Clean_Branco_BB9616_BB9616_01_standard.jpg", "jpg"));

        ProductRepository productRepository = new MemoryProductRepository();
        productRepository.save(alpha);
        productRepository.save(neo);
    }
}

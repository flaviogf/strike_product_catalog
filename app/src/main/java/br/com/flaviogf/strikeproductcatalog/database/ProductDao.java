package br.com.flaviogf.strikeproductcatalog.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
import java.util.UUID;

import br.com.flaviogf.strikeproductcatalog.models.Product;

@Dao
public interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(Product product);

    @Query("SELECT * FROM product")
    List<Product> findAll();

    @Query("SELECT * FROM product WHERE id = :id")
    Product findOne(UUID id);
}

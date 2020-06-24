package br.com.flaviogf.strikeproductcatalog.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import br.com.flaviogf.strikeproductcatalog.models.Image;
import br.com.flaviogf.strikeproductcatalog.models.Product;

@Database(entities = {Product.class}, version = 1, exportSchema = false)
@TypeConverters(StrikeProductCatalogDatabase.Converters.class)
public abstract class StrikeProductCatalogDatabase extends RoomDatabase {
    public abstract ProductDao productDao();

    public static class Converters {
        @TypeConverter
        public UUID stringToUUID(String value) {
            return UUID.fromString(value);
        }

        @TypeConverter
        public String UUIDToString(UUID value) {
            return value.toString();
        }

        @TypeConverter
        public Double bigDecimalToDouble(BigDecimal value) {
            return value.doubleValue();
        }

        @TypeConverter
        public BigDecimal doubleToBigDecimal(Double value) {
            return new BigDecimal(value);
        }

        @TypeConverter
        public String imageListToString(List<Image> value) {
            Gson gson = new Gson();
            return gson.toJson(value);
        }

        @TypeConverter
        public List<Image> stringToImageList(String value) {
            Gson gson = new Gson();

            Type type = new TypeToken<List<Image>>() {
            }.getType();

            return gson.fromJson(value, type);
        }
    }
}

package br.com.flaviogf.strikeproductcatalog.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import br.com.flaviogf.strikeproductcatalog.infrastructure.Result;
import br.com.flaviogf.strikeproductcatalog.models.Product;
import br.com.flaviogf.strikeproductcatalog.repositories.ProductRepository;

public class ProductsViewModel extends ViewModel {
    private ProductRepository productRepository;

    public ProductsViewModel(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public LiveData<Result<List<Product>>> fetch() {
        MutableLiveData<Result<List<Product>>> liveData = new MutableLiveData<>();

        Result<List<Product>> result = productRepository.fetch();

        liveData.setValue(result);

        return liveData;
    }
}

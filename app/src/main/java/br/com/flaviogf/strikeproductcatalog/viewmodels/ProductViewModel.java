package br.com.flaviogf.strikeproductcatalog.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import br.com.flaviogf.strikeproductcatalog.infrastructure.Result;
import br.com.flaviogf.strikeproductcatalog.models.Product;
import br.com.flaviogf.strikeproductcatalog.repositories.ProductRepository;

public class ProductViewModel extends ViewModel {
    private final ProductRepository productRepository;

    public ProductViewModel(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public LiveData<Result<Void>> save(Product product) {
        MutableLiveData<Result<Void>> liveData = new MutableLiveData<>();

        Result<Void> result = productRepository.save(product);

        liveData.setValue(result);

        return liveData;
    }
}

package br.com.flaviogf.strikeproductcatalog.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import br.com.flaviogf.strikeproductcatalog.repositories.ProductRepository;

public class ProductViewModelFactory implements ViewModelProvider.Factory {
    private final ProductRepository productRepository;

    public ProductViewModelFactory(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ProductViewModel(productRepository);
    }
}

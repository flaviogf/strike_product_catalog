package br.com.flaviogf.strikeproductcatalog.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import br.com.flaviogf.strikeproductcatalog.repositories.ProductRepository;

public class ProductsViewModelFactory implements ViewModelProvider.Factory {
    private ProductRepository productRepository;

    public ProductsViewModelFactory(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ProductsViewModel(productRepository);
    }
}

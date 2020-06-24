package br.com.flaviogf.strikeproductcatalog.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import br.com.flaviogf.strikeproductcatalog.repositories.ProductRepository;

public class ImageViewModelFactory implements ViewModelProvider.Factory {
    private final ProductRepository productRepository;

    public ImageViewModelFactory(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ImageViewModel(productRepository);
    }
}

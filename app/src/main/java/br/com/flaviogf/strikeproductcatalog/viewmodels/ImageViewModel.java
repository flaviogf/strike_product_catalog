package br.com.flaviogf.strikeproductcatalog.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.UUID;

import br.com.flaviogf.strikeproductcatalog.infrastructure.Maybe;
import br.com.flaviogf.strikeproductcatalog.infrastructure.Result;
import br.com.flaviogf.strikeproductcatalog.models.Image;
import br.com.flaviogf.strikeproductcatalog.models.Product;
import br.com.flaviogf.strikeproductcatalog.repositories.ProductRepository;

public class ImageViewModel extends ViewModel {
    private final ProductRepository productRepository;

    public ImageViewModel(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public LiveData<Result<Image>> fetchOne(UUID productId, UUID imageId) {
        MutableLiveData<Result<Image>> liveData = new MutableLiveData<>();

        Result<Product> result = productRepository.fetchOne(productId);

        if (result.isFailure()) {
            liveData.setValue(Result.fail(result.getMessage()));
            return liveData;
        }

        Product product = result.getValue();

        Maybe<Image> maybeImage = product.getImage(imageId);

        if (!maybeImage.hasValue()) {
            liveData.setValue(Result.fail("Image does not exist"));
            return liveData;
        }

        Image image = maybeImage.getValue();

        liveData.setValue(Result.ok(image));

        return liveData;
    }

    public LiveData<Result<Void>> remove(UUID productId, UUID imageId) {
        MutableLiveData<Result<Void>> liveData = new MutableLiveData<>();

        Result<Product> productResult = productRepository.fetchOne(productId);

        if (productResult.isFailure()) {
            liveData.setValue(Result.fail(productResult.getMessage()));
            return liveData;
        }

        Product product = productResult.getValue();

        Maybe<Image> maybeImage = product.getImage(imageId);

        if (!maybeImage.hasValue()) {
            liveData.setValue(Result.fail("Image does not exist"));
            return liveData;
        }

        Image image = maybeImage.getValue();

        Result<Void> removeResult = product.remove(image);

        if (removeResult.isFailure()) {
            liveData.setValue(Result.fail(removeResult.getMessage()));
            return liveData;
        }

        Result<Void> result = productRepository.save(product);

        liveData.setValue(result);

        return liveData;
    }
}

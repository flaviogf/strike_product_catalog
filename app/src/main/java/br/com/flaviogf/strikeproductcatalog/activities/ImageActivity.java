package br.com.flaviogf.strikeproductcatalog.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.UUID;

import br.com.flaviogf.strikeproductcatalog.R;
import br.com.flaviogf.strikeproductcatalog.StrikeProductCatalogApplication;
import br.com.flaviogf.strikeproductcatalog.infrastructure.Maybe;
import br.com.flaviogf.strikeproductcatalog.models.Image;
import br.com.flaviogf.strikeproductcatalog.repositories.ProductRepository;
import br.com.flaviogf.strikeproductcatalog.viewmodels.ImageViewModel;
import br.com.flaviogf.strikeproductcatalog.viewmodels.ImageViewModelFactory;

public class ImageActivity extends AppCompatActivity {
    private ImageView imageImageView;
    private FloatingActionButton removeFloatingActionButton;
    private ImageViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        getSupportActionBar().hide();

        imageImageView = findViewById(R.id.activity_image_image_view);

        removeFloatingActionButton = findViewById(R.id.activity_image_remove_button);

        removeFloatingActionButton.setOnClickListener(it -> {
            new AlertDialog
                    .Builder(this)
                    .setTitle("Remove Image")
                    .setMessage("Are you sure you want to remove this image?")
                    .setNegativeButton("Cancel", null)
                    .setPositiveButton("Confirm", ((dialogInterface, i) -> {
                        removeImage();
                    })).show();
        });

        StrikeProductCatalogApplication application = (StrikeProductCatalogApplication) getApplication();

        ProductRepository productRepository = application.productRepository();

        ImageViewModelFactory factory = new ImageViewModelFactory(productRepository);

        viewModel = new ViewModelProvider(this, factory).get(ImageViewModel.class);

        fillFields();
    }

    private void fillFields() {
        Maybe<UUID> maybeProductId = Maybe.of((UUID) getIntent().getSerializableExtra("@product-id"));

        if (!maybeProductId.hasValue()) {
            finish();
            return;
        }

        Maybe<UUID> maybeImageId = Maybe.of((UUID) getIntent().getSerializableExtra("@image-id"));

        if (!maybeImageId.hasValue()) {
            finish();
            return;
        }

        UUID productId = maybeProductId.getValue();

        UUID imageId = maybeImageId.getValue();

        viewModel.fetchOne(productId, imageId).observe(this, result -> {
            if (result.isFailure()) {
                Toast.makeText(this, result.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
                return;
            }

            Image image = result.getValue();

            Picasso.get().load(image.getPath()).fit().centerCrop().into(imageImageView);
        });
    }

    private void removeImage() {
        Maybe<UUID> maybeProductId = Maybe.of((UUID) getIntent().getSerializableExtra("@product-id"));

        if (!maybeProductId.hasValue()) {
            setResult(RESULT_CANCELED);
            finish();
            return;
        }

        Maybe<UUID> maybeImageId = Maybe.of((UUID) getIntent().getSerializableExtra("@image-id"));

        if (!maybeImageId.hasValue()) {
            setResult(RESULT_CANCELED);
            finish();
            return;
        }

        UUID productId = maybeProductId.getValue();

        UUID imageId = maybeImageId.getValue();

        viewModel.remove(productId, imageId).observe(this, result -> {
            if (result.isFailure()) {
                Toast.makeText(this, result.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }

            setResult(RESULT_OK);
            finish();
        });
    }
}

package br.com.flaviogf.strikeproductcatalog.activities;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.math.BigDecimal;
import java.util.UUID;

import br.com.flaviogf.strikeproductcatalog.R;
import br.com.flaviogf.strikeproductcatalog.adapters.ImageListAdapter;
import br.com.flaviogf.strikeproductcatalog.infrastructure.Maybe;
import br.com.flaviogf.strikeproductcatalog.models.Image;
import br.com.flaviogf.strikeproductcatalog.models.Product;
import br.com.flaviogf.strikeproductcatalog.repositories.MemoryProductRepository;
import br.com.flaviogf.strikeproductcatalog.repositories.ProductRepository;
import br.com.flaviogf.strikeproductcatalog.viewmodels.ProductViewModel;
import br.com.flaviogf.strikeproductcatalog.viewmodels.ProductViewModelFactory;

public class ProductActivity extends AppCompatActivity {
    public static final int REQUEST_IMAGE_CODE = 1;

    private ImageListAdapter imageListAdapter;
    private ImageButton addImageImageButton;
    private RecyclerView imageRecyclerView;
    private TextInputLayout nameTextInputLayout;
    private TextInputLayout priceTextInputLayout;
    private TextInputLayout descriptionTextInputLayout;
    private Spinner categoriesSpinner;
    private Button saveButton;
    private ProductViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        setTitle("Product");

        imageListAdapter = new ImageListAdapter(this);

        addImageImageButton = findViewById(R.id.activity_product_add_image_image_button);

        imageRecyclerView = findViewById(R.id.activity_product_image_recycler_view);

        nameTextInputLayout = findViewById(R.id.activity_product_name_text_input_layout);

        priceTextInputLayout = findViewById(R.id.activity_product_price_text_input_layout);

        descriptionTextInputLayout = findViewById(R.id.activity_product_description_text_input_layout);

        categoriesSpinner = findViewById(R.id.activity_product_categories_spinner);

        saveButton = findViewById(R.id.activity_product_save_button);

        imageRecyclerView.setAdapter(imageListAdapter);

        addImageImageButton.setOnClickListener(it -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "Choose one"), REQUEST_IMAGE_CODE);
        });

        saveButton.setOnClickListener(it -> {
            saveProduct();
        });

        ProductRepository productRepository = new MemoryProductRepository();

        ProductViewModelFactory factory = new ProductViewModelFactory(productRepository);

        viewModel = new ViewModelProvider(this, factory).get(ProductViewModel.class);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CODE && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();

            if (uri == null) {
                return;
            }

            Maybe<Image> image = getImage(uri);

            if (!image.hasValue()) {
                return;
            }

            imageListAdapter.add(image.getValue());
        }
    }

    private void saveProduct() {
        Maybe<Product> maybeProduct = getProduct();

        if (!maybeProduct.hasValue()) {
            Toast.makeText(this, "Enter all data", Toast.LENGTH_SHORT).show();
            return;
        }

        Product product = maybeProduct.getValue();

        viewModel.save(product).observe(this, result -> {
            if (result.isFailure()) {
                Toast.makeText(this, result.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }

            finish();
        });
    }

    private Maybe<Product> getProduct() {
        String name = nameTextInputLayout.getEditText().getText().toString();

        String price = priceTextInputLayout.getEditText().getText().toString();

        String description = descriptionTextInputLayout.getEditText().getText().toString();

        String category = categoriesSpinner.getSelectedItem().toString();

        Image[] images = imageListAdapter.getImages();

        if (name.isEmpty()) {
            return Maybe.empty();
        }

        if (price.isEmpty()) {
            return Maybe.empty();
        }

        if (description.isEmpty()) {
            return Maybe.empty();
        }

        if (category.isEmpty()) {
            return Maybe.empty();
        }

        if (images.length == 0) {
            return Maybe.empty();
        }

        Product product = new Product(UUID.randomUUID(), name, description, new BigDecimal(price), category);

        product.addImage(images);

        return Maybe.of(product);
    }

    private Maybe<Image> getImage(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};

        Cursor cursor = managedQuery(uri, projection, null, null, null);
        cursor.moveToNext();
        String pathname = cursor.getString(0);

        if (pathname == null) {
            return Maybe.empty();
        }

        File file = new File(pathname);
        String name = file.getName();
        String path = uri.toString();
        String ext = file.getAbsolutePath().split("\\.")[1];

        Image image = new Image(UUID.randomUUID(), name, path, ext);

        return Maybe.of(image);
    }
}

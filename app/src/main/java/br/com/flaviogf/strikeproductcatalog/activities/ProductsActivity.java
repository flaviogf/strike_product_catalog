package br.com.flaviogf.strikeproductcatalog.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import br.com.flaviogf.strikeproductcatalog.R;
import br.com.flaviogf.strikeproductcatalog.StrikeProductCatalogApplication;
import br.com.flaviogf.strikeproductcatalog.adapters.ProductListAdapter;
import br.com.flaviogf.strikeproductcatalog.models.Product;
import br.com.flaviogf.strikeproductcatalog.repositories.ProductRepository;
import br.com.flaviogf.strikeproductcatalog.viewmodels.ProductsViewModel;
import br.com.flaviogf.strikeproductcatalog.viewmodels.ProductsViewModelFactory;

public class ProductsActivity extends AppCompatActivity {
    private ProductListAdapter productListAdapter;
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private ProductsViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        setTitle("Products");

        productListAdapter = new ProductListAdapter(this);

        recyclerView = findViewById(R.id.activity_products_recycler_view);

        floatingActionButton = findViewById(R.id.activity_products_floating_action_button);

        productListAdapter.setOnProductSelectedListener((it) -> {
            Intent intent = new Intent(this, ProductActivity.class);
            intent.putExtra("@product-id", it.getId());
            startActivity(intent);
        });

        recyclerView.setAdapter(productListAdapter);

        floatingActionButton.setOnClickListener(it -> {
            Intent intent = new Intent(this, ProductActivity.class);
            startActivity(intent);
        });

        StrikeProductCatalogApplication application = (StrikeProductCatalogApplication) getApplication();

        ProductRepository productRepository = application.productRepository();

        ProductsViewModelFactory factory = new ProductsViewModelFactory(productRepository);

        viewModel = new ViewModelProvider(this, factory).get(ProductsViewModel.class);
    }

    @Override
    protected void onResume() {
        super.onResume();

        viewModel.fetch().observe(this, (result) -> {
            if (result.isFailure()) {
                Toast.makeText(this, result.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }

            List<Product> products = result.getValue();

            productListAdapter.setValue(products);
        });
    }
}

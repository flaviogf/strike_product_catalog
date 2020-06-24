package br.com.flaviogf.strikeproductcatalog.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.flaviogf.strikeproductcatalog.R;
import br.com.flaviogf.strikeproductcatalog.infrastructure.Maybe;
import br.com.flaviogf.strikeproductcatalog.models.Image;
import br.com.flaviogf.strikeproductcatalog.models.Product;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {
    private final Context context;
    private final List<Product> products = new ArrayList<>();
    private OnProductSelectedListener onProductSelectedListener;

    public ProductListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view, onProductSelectedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = products.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setValue(List<Product> products) {
        this.products.clear();
        this.products.addAll(products);
        notifyDataSetChanged();
    }

    public void setOnProductSelectedListener(OnProductSelectedListener onProductSelectedListener) {
        this.onProductSelectedListener = onProductSelectedListener;
    }

    public interface OnProductSelectedListener {
        void onSelect(Product product);
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        private final OnProductSelectedListener onProductSelectedListener;
        private final ImageView imageImageView;
        private final TextView nameTextView;
        private final TextView descriptionTextView;
        private final TextView priceTextView;
        private final TextView categoryTextView;

        public ProductViewHolder(@NonNull View itemView, OnProductSelectedListener onProductSelectedListener) {
            super(itemView);

            this.onProductSelectedListener = onProductSelectedListener;
            imageImageView = itemView.findViewById(R.id.item_product_image_image_view);
            nameTextView = itemView.findViewById(R.id.item_product_name_text_view);
            descriptionTextView = itemView.findViewById(R.id.item_product_description_text_view);
            priceTextView = itemView.findViewById(R.id.item_product_price_text_view);
            categoryTextView = itemView.findViewById(R.id.item_product_category_text_view);
        }

        public void bind(Product product) {
            itemView.setOnClickListener((it) -> {
                onProductSelectedListener.onSelect(product);
            });

            nameTextView.setText(product.getName());
            descriptionTextView.setText(product.getDescription());
            NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
            priceTextView.setText(numberFormat.format(product.getPrice()));
            categoryTextView.setText(product.getCategory());

            Maybe<Image> maybeImage = product.getPrincipalImage();

            if (!maybeImage.hasValue()) {
                return;
            }

            Image image = maybeImage.getValue();

            Picasso.get().load(image.getPath()).fit().centerCrop().into(imageImageView);
        }
    }
}

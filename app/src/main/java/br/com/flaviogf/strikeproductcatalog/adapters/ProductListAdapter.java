package br.com.flaviogf.strikeproductcatalog.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.flaviogf.strikeproductcatalog.R;
import br.com.flaviogf.strikeproductcatalog.models.Product;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {
    private final Context context;
    private final List<Product> products = new ArrayList<>();

    public ProductListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
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

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageImageView;
        private final TextView nameTextView;
        private final TextView descriptionTextView;
        private final TextView colorTextView;
        private final TextView priceTextView;
        private final TextView categoryTextView;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            imageImageView = itemView.findViewById(R.id.item_product_image_image_view);
            nameTextView = itemView.findViewById(R.id.item_product_name_text_view);
            descriptionTextView = itemView.findViewById(R.id.item_product_description_text_view);
            colorTextView = itemView.findViewById(R.id.item_product_color_text_view);
            priceTextView = itemView.findViewById(R.id.item_product_price_text_view);
            categoryTextView = itemView.findViewById(R.id.item_product_category_text_view);
        }

        public void bind(Product product) {
            nameTextView.setText(product.getName());
            priceTextView.setText(product.getPrice().toString());
            categoryTextView.setText(product.getCategory());
        }
    }
}

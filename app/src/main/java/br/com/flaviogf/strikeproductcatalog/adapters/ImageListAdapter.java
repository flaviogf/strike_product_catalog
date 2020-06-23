package br.com.flaviogf.strikeproductcatalog.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import br.com.flaviogf.strikeproductcatalog.R;
import br.com.flaviogf.strikeproductcatalog.models.Image;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ImageViewHolder> {
    private final Context context;
    private final List<Image> images = new ArrayList<>();

    public ImageListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Image image = images.get(position);
        holder.bind(image);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public void add(Image image) {
        images.add(image);
        notifyDataSetChanged();
    }

    public void setImages(Collection<Image> images) {
        this.images.clear();
        this.images.addAll(images);
    }

    public Collection<Image> getImages() {
        return Collections.unmodifiableList(images);
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.item_uri_image_view);
        }

        public void bind(Image image) {
            Picasso.get().load(image.getPath()).resize(100, 100).centerCrop().into(imageView);
        }
    }
}

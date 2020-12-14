package ccfit.nsu.ru.khudyakov.newsfeed.features.photo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.ArrayList;
import java.util.List;

import ccfit.nsu.ru.khudyakov.newsfeed.R;
import ccfit.nsu.ru.khudyakov.newsfeed.model.photo.VKPhoto;

import static ccfit.nsu.ru.khudyakov.newsfeed.utils.BindUtils.bindImage;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoHolder> {

    private Context context;

    private List<VKPhoto> photos = new ArrayList<>();

    public PhotoAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public PhotoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(context).inflate(R.layout.item_photo, parent, false);
        return new PhotoAdapter.PhotoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoHolder holder, int position) {
        holder.bind(photos.get(position));
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public void setItems(List<VKPhoto> photos) {
        this.photos.clear();
        this.photos.addAll(photos);
        notifyDataSetChanged();
    }

    static class PhotoHolder extends RecyclerView.ViewHolder {

        public PhotoHolder(@NonNull View itemView) {
            super(itemView);
        }

        private void bind(VKPhoto vkPhoto) {
            ImageView imageView = itemView.findViewById(R.id.post_image);
            bindImage(imageView, vkPhoto.getUrl());
            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            if (layoutParams instanceof FlexboxLayoutManager.LayoutParams) {
                ((FlexboxLayoutManager.LayoutParams) layoutParams).setFlexGrow(1f);
            }
        }
    }
}

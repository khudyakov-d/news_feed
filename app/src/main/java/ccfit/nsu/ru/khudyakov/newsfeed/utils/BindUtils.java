package ccfit.nsu.ru.khudyakov.newsfeed.utils;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.squareup.picasso.Picasso;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.List;

import ccfit.nsu.ru.khudyakov.newsfeed.R;
import ccfit.nsu.ru.khudyakov.newsfeed.features.photo.PhotoAdapter;
import ccfit.nsu.ru.khudyakov.newsfeed.model.photo.VKPhoto;
import ccfit.nsu.ru.khudyakov.newsfeed.model.source.VKGroup;
import ccfit.nsu.ru.khudyakov.newsfeed.model.source.VKProfile;
import ccfit.nsu.ru.khudyakov.newsfeed.model.source.VKSource;

import static java.time.format.DateTimeFormatter.ofPattern;

public class BindUtils {

    public static void bindSource(View itemView, VKSource vkSource, LocalDateTime date) {
        ImageView imageView = itemView.findViewById(R.id.source_image);
        bindImage(imageView, vkSource.getPhotoUrl());

        TextView textView = itemView.findViewById(R.id.source_name);

        if (vkSource instanceof VKGroup) {
            VKGroup vkGroup = (VKGroup) vkSource;
            textView.setText(vkGroup.getName());
        } else if (vkSource instanceof VKProfile) {
            VKProfile vkProfile = (VKProfile) vkSource;
            textView.setText(MessageFormat
                    .format("{0} {1}", vkProfile.getFirstName(), vkProfile.getLastName())
            );
        }

        TextView dateView = itemView.findViewById(R.id.date);
        dateView.setText(date.format(ofPattern("dd.MM.yyyy hh:mm:ss")));
    }

    public static void bindPhotos(Context context, View itemView, List<VKPhoto> vkPhotos) {
        RecyclerView photosView = itemView.findViewById(R.id.photos_list);
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(context);

        flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
        flexboxLayoutManager.setAlignItems(AlignItems.STRETCH);

        photosView.setLayoutManager(flexboxLayoutManager);

        PhotoAdapter photoAdapter = new PhotoAdapter(context);
        photosView.setAdapter(photoAdapter);
        photoAdapter.setItems(vkPhotos);
    }

    public static void bindImage(ImageView imageView, String url) {
        Picasso.get()
                .load(url)
                .into(imageView);
    }

}

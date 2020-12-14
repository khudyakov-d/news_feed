package ccfit.nsu.ru.khudyakov.newsfeed.features.newsfeed;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ccfit.nsu.ru.khudyakov.newsfeed.R;
import ccfit.nsu.ru.khudyakov.newsfeed.model.post.VKPostItem;

import static ccfit.nsu.ru.khudyakov.newsfeed.utils.BindUtils.bindPhotos;
import static ccfit.nsu.ru.khudyakov.newsfeed.utils.BindUtils.bindSource;

public class PostHolder extends RecyclerView.ViewHolder {

    private Context context;

    public PostHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.context = context;
    }

    public void bind(VKPostItem vkPostItem) {
        bindSource(itemView, vkPostItem.getVkSource(), vkPostItem.getVkPost().getDate());
        bindPhotos(context, itemView, vkPostItem.getVkPost().getPhotos());
        bindText(vkPostItem);
    }

    public void bind(VKPostItem vkPostItem, View.OnClickListener onItemClickListener) {
        itemView.setOnClickListener(onItemClickListener);
        bind(vkPostItem);
    }

    private void bindText(VKPostItem vkPostItem) {
        TextView postTextView = itemView.findViewById(R.id.post_text);
        postTextView.setText(vkPostItem.getVkPost().getText());

        TextView likesCountTextView = itemView.findViewById(R.id.likes_count);
        likesCountTextView.setText(String.valueOf(vkPostItem.getVkPost().getLikesCount()));

        TextView repostCountTextView = itemView.findViewById(R.id.repost_count);
        repostCountTextView.setText(String.valueOf(vkPostItem.getVkPost().getRepostsCount()));

        TextView commentCountTextView = itemView.findViewById(R.id.comment_count);
        commentCountTextView.setText(String.valueOf(vkPostItem.getVkPost().getCommentsCount()));
    }

}

package ccfit.nsu.ru.khudyakov.newsfeed.features.comments;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ccfit.nsu.ru.khudyakov.newsfeed.R;
import ccfit.nsu.ru.khudyakov.newsfeed.model.comment.VKPostComment;
import ccfit.nsu.ru.khudyakov.newsfeed.model.comment.VKPostCommentItem;

import static ccfit.nsu.ru.khudyakov.newsfeed.utils.BindUtils.bindPhotos;
import static ccfit.nsu.ru.khudyakov.newsfeed.utils.BindUtils.bindSource;

public class CommentsHolder extends RecyclerView.ViewHolder {

    private Context context;

    public CommentsHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.context = context;
    }

    public void bind(VKPostCommentItem vkPostCommentItem) {
        bindSource(itemView, vkPostCommentItem.getVkSource(), vkPostCommentItem.getVkPostComment().getDate());
        bindPhotos(context, itemView, vkPostCommentItem.getVkPostComment().getPhotos());
        bindText(vkPostCommentItem.getVkPostComment());
    }

    private void bindText(VKPostComment vkPostComment) {
        TextView commentTextView = itemView.findViewById(R.id.comment_text);
        commentTextView.setText(vkPostComment.getText());
    }

}

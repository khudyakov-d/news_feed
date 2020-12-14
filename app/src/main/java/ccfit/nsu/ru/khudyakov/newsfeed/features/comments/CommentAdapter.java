package ccfit.nsu.ru.khudyakov.newsfeed.features.comments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import ccfit.nsu.ru.khudyakov.newsfeed.R;
import ccfit.nsu.ru.khudyakov.newsfeed.model.comment.VKPostCommentItem;

public class CommentAdapter extends PagedListAdapter<VKPostCommentItem, CommentsHolder> {

    private Context context;

    public CommentAdapter(Context context) {
        super(new DiffUtil.ItemCallback<VKPostCommentItem>() {
            @Override
            public boolean areItemsTheSame(@NonNull VKPostCommentItem oldItem, @NonNull VKPostCommentItem newItem) {
                return oldItem.getVkPostComment().getId().equals(newItem.getVkPostComment().getId());
            }

            @Override
            public boolean areContentsTheSame(@NonNull VKPostCommentItem oldItem, @NonNull VKPostCommentItem newItem) {
                return oldItem.equals(newItem);
            }
        });

        this.context = context;

    }

    @NonNull
    @Override
    public CommentsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(context).inflate(R.layout.item_comment, parent, false);
        return new CommentsHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsHolder holder, int position) {
        VKPostCommentItem vkPostCommentItem = getItem(position);
        if (vkPostCommentItem != null) {
            holder.bind(vkPostCommentItem);
        }
    }

}

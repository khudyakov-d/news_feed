package ccfit.nsu.ru.khudyakov.newsfeed.features.newsfeed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import ccfit.nsu.ru.khudyakov.newsfeed.R;
import ccfit.nsu.ru.khudyakov.newsfeed.features.comments.CommentsActivity;
import ccfit.nsu.ru.khudyakov.newsfeed.model.post.VKPostItem;

public class NewsFeedAdapter extends PagedListAdapter<VKPostItem, PostHolder> {

    private Context context;

    protected NewsFeedAdapter(Context context) {
        super(new DiffUtil.ItemCallback<VKPostItem>() {
            @Override
            public boolean areItemsTheSame(@NonNull VKPostItem oldItem, @NonNull VKPostItem newItem) {
                return oldItem.getVkPost().getPostId().equals(newItem.getVkPost().getPostId());
            }

            @Override
            public boolean areContentsTheSame(@NonNull VKPostItem oldItem, @NonNull VKPostItem newItem) {
                return oldItem.equals(newItem);
            }
        });
        this.context = context;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new PostHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        VKPostItem vkPostItem = getItem(position);
        if (vkPostItem != null) {
            holder.bind(vkPostItem, new OnPostItemClickListener(context, vkPostItem));
        }
    }

    static class OnPostItemClickListener implements View.OnClickListener {

        private Context context;

        private VKPostItem vkPostItem;

        public OnPostItemClickListener(Context context, VKPostItem vkPostItem) {
            this.context = context;
            this.vkPostItem = vkPostItem;
        }

        @Override
        public void onClick(View v) {
            CommentsActivity.start(context, vkPostItem.getVkPost().getPostId(), vkPostItem.getVkPost().getSourceId());
        }
    }

}


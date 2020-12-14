package ccfit.nsu.ru.khudyakov.newsfeed.features.comments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ccfit.nsu.ru.khudyakov.newsfeed.R;

import static java.util.Objects.requireNonNull;

public class CommentsActivity extends AppCompatActivity {

    private final static String POST_ID = "post_id";

    private final static String OWNER_ID = "owner_id";

    public static void start(final Context context, int postId, int ownerId) {
        Intent intent = new Intent(context, CommentsActivity.class);
        intent.putExtra(POST_ID, postId);
        intent.putExtra(OWNER_ID, ownerId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_post_comments);

        int postId = requireNonNull(getIntent().getExtras()).getInt(POST_ID);
        int ownerId = requireNonNull(getIntent().getExtras()).getInt(OWNER_ID);

        RecyclerView commentListView = findViewById(R.id.comments_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        commentListView.setLayoutManager(layoutManager);

        CommentAdapter commentAdapter = new CommentAdapter(this);
        final CommentsViewModel viewModel = new ViewModelProvider(
                this, new CommentsViewModelFactory(ownerId, postId))
                .get(CommentsViewModel.class);

        viewModel.getItemPagedList().observe(this, commentAdapter::submitList);
        commentListView.setAdapter(commentAdapter);

        commentListView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

}

package ccfit.nsu.ru.khudyakov.newsfeed.features.newsfeed;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import ccfit.nsu.ru.khudyakov.newsfeed.R;

import static java.util.Objects.requireNonNull;

public class NewsFeedActivity extends AppCompatActivity {

    private RecyclerView postListView;

    private static final String LAYOUT_MANAGER_STATE = "LAYOUT_MANAGER_STATE";

    private Parcelable layoutManagerState;

    public static void start(final Context context) {
        Intent intent = new Intent(context, NewsFeedActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        postListView = findViewById(R.id.news_feed);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        postListView.setLayoutManager(layoutManager);

        NewsFeedAdapter newsFeedAdapter = new NewsFeedAdapter(this);

        final NewsFeedViewModel viewModel = new ViewModelProvider.NewInstanceFactory().create(NewsFeedViewModel.class);
        viewModel.getItemPagedList().observe(this, newsFeedAdapter::submitList);

        postListView.setAdapter(newsFeedAdapter);
        postListView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        /*
          Does not work. I do not know why
         */
        if (savedInstanceState != null) {
            layoutManagerState = savedInstanceState.getParcelable(LAYOUT_MANAGER_STATE);
            requireNonNull(postListView.getLayoutManager()).onRestoreInstanceState(layoutManagerState);
        }

        setDivider(postListView);
    }

    private void setDivider(RecyclerView recyclerView) {
        DividerItemDecoration divider = new DividerItemDecoration(
                recyclerView.getContext(),
                DividerItemDecoration.VERTICAL
        );

        divider.setDrawable(requireNonNull(ContextCompat.getDrawable(getBaseContext(),
                R.drawable.recycler_view_divider)));
        recyclerView.addItemDecoration(divider);
    }

    @Override
    protected void onPause() {
        super.onPause();
        layoutManagerState = requireNonNull(postListView.getLayoutManager()).onSaveInstanceState();
    }


    @Override
    protected void onSaveInstanceState(@NotNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putParcelable(LAYOUT_MANAGER_STATE, layoutManagerState);
    }
}
package ccfit.nsu.ru.khudyakov.newsfeed.features.comments;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import ccfit.nsu.ru.khudyakov.newsfeed.features.comments.data.VKPostCommentsDataSource;
import ccfit.nsu.ru.khudyakov.newsfeed.features.comments.data.VKPostCommentsDataSourceFactory;
import ccfit.nsu.ru.khudyakov.newsfeed.model.comment.VKPostCommentItem;

public class CommentsViewModel extends ViewModel {

    private LiveData<PagedList<VKPostCommentItem>> itemPagedList;

    public CommentsViewModel(int ownerId, int postId) {
        VKPostCommentsDataSourceFactory dataSourceFactory = new VKPostCommentsDataSourceFactory(ownerId, postId);

        PagedList.Config pagedListConfig = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(VKPostCommentsDataSource.getPageSize())
                .build();

        itemPagedList = new LivePagedListBuilder<>(dataSourceFactory, pagedListConfig).build();
    }

    public LiveData<PagedList<VKPostCommentItem>> getItemPagedList() {
        return itemPagedList;
    }

}

package ccfit.nsu.ru.khudyakov.newsfeed.features.comments.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import ccfit.nsu.ru.khudyakov.newsfeed.model.comment.VKPostCommentItem;

public class VKPostCommentsDataSourceFactory extends DataSource.Factory<Integer, VKPostCommentItem> {

    private final int ownerId;
    private final int postId;
    private MutableLiveData<PageKeyedDataSource<Integer, VKPostCommentItem>> liveDataSource = new MutableLiveData<>();

    public VKPostCommentsDataSourceFactory(int ownerId, int postId) {
        this.ownerId = ownerId;
        this.postId = postId;
    }

    @NonNull
    @Override
    public DataSource<Integer, VKPostCommentItem> create() {
        VKPostCommentsDataSource dataSource = new VKPostCommentsDataSource(ownerId, postId);
        liveDataSource.postValue(dataSource);
        return dataSource;
    }

}


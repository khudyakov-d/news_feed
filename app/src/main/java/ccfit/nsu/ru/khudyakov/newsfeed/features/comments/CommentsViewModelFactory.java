package ccfit.nsu.ru.khudyakov.newsfeed.features.comments;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class CommentsViewModelFactory implements ViewModelProvider.Factory {

    private final int ownerId;

    private final int postId;

    public CommentsViewModelFactory(int ownerId, int postId) {
        this.ownerId = ownerId;
        this.postId = postId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new CommentsViewModel(ownerId, postId);
    }
}

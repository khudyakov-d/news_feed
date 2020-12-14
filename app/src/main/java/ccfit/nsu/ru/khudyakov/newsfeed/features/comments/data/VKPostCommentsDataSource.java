package ccfit.nsu.ru.khudyakov.newsfeed.features.comments.data;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.vk.api.sdk.VK;
import com.vk.api.sdk.VKApiCallback;
import com.vk.api.sdk.exceptions.VKApiExecutionException;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import ccfit.nsu.ru.khudyakov.newsfeed.model.comment.VKPostComment;
import ccfit.nsu.ru.khudyakov.newsfeed.model.comment.VKPostCommentData;
import ccfit.nsu.ru.khudyakov.newsfeed.model.comment.VKPostCommentItem;
import ccfit.nsu.ru.khudyakov.newsfeed.model.source.VKSource;

public class VKPostCommentsDataSource extends PageKeyedDataSource<Integer, VKPostCommentItem> {

    private static final int PAGE_SIZE = 10;

    private final int ownerId;

    private final int postId;

    public VKPostCommentsDataSource(int ownerId, int postId) {
        this.ownerId = ownerId;
        this.postId = postId;
    }

    public static int getPageSize() {
        return PAGE_SIZE;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, VKPostCommentItem> callback) {
        VK.execute(new VKGetPostCommentsRequest(ownerId, postId, 0, PAGE_SIZE),
                new VKApiCallback<VKPostCommentData>() {
                    @Override
                    public void success(VKPostCommentData vkPostCommentData) {
                        List<VKPostCommentItem> vkPostCommentItems = getPostCommentItems(vkPostCommentData);
                        callback.onResult(vkPostCommentItems, null, vkPostCommentItems.size());
                    }

                    @Override
                    public void fail(@NotNull VKApiExecutionException e) {
                        e.printStackTrace();
                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, VKPostCommentItem> callback) {
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, VKPostCommentItem> callback) {
        VK.execute(new VKGetPostCommentsRequest(ownerId, postId, params.key, PAGE_SIZE),
                new VKApiCallback<VKPostCommentData>() {
                    @Override
                    public void success(VKPostCommentData vkPostCommentData) {
                        List<VKPostCommentItem> vkPostCommentItems = getPostCommentItems(vkPostCommentData);
                        callback.onResult(vkPostCommentItems, params.key + vkPostCommentItems.size());
                    }

                    @Override
                    public void fail(@NotNull VKApiExecutionException e) {
                        e.printStackTrace();
                    }
                });
    }

    private List<VKPostCommentItem> getPostCommentItems(VKPostCommentData vkPostCommentData) {
        List<VKPostCommentItem> vkPostCommentItems = new ArrayList<>();

        for (VKPostComment vkPostComment : vkPostCommentData.getComments()) {
            for (VKSource vkSource : vkPostCommentData.getSources()) {
                int fromId = vkPostComment.getFromId() < 0 ?
                        vkPostComment.getFromId() * (-1) :
                        vkPostComment.getFromId();

                if (fromId == vkSource.getId()) {
                    vkPostCommentItems.add(new VKPostCommentItem(vkPostComment, vkSource));
                    break;
                }
            }
        }

        return vkPostCommentItems;
    }
}


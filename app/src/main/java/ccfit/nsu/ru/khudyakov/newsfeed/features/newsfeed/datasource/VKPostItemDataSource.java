package ccfit.nsu.ru.khudyakov.newsfeed.features.newsfeed.datasource;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.vk.api.sdk.VK;
import com.vk.api.sdk.VKApiCallback;
import com.vk.api.sdk.exceptions.VKApiExecutionException;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import ccfit.nsu.ru.khudyakov.newsfeed.model.post.VKPost;
import ccfit.nsu.ru.khudyakov.newsfeed.model.post.VKPostInfo;
import ccfit.nsu.ru.khudyakov.newsfeed.model.post.VKPostItem;
import ccfit.nsu.ru.khudyakov.newsfeed.model.source.VKSource;

public class VKPostItemDataSource extends PageKeyedDataSource<String, VKPostItem> {

    private static final int PAGE_SIZE = 5;

    private static final int FIRST_PAGE = 0;

    public static int getPageSize() {
        return PAGE_SIZE;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<String> params, @NonNull LoadInitialCallback<String, VKPostItem> callback) {
        VK.execute(new VKGetNewsFeedRequest(null, PAGE_SIZE), new VKApiCallback<VKPostInfo>() {
            @Override
            public void success(VKPostInfo vkPostInfo) {
                List<VKPostItem> vkPostItems = getPostItems(vkPostInfo);
                callback.onResult(vkPostItems, null, vkPostInfo.getNextFrom());
            }

            @Override
            public void fail(@NotNull VKApiExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, VKPostItem> callback) {

    }


    @Override
    public void loadAfter(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, VKPostItem> callback) {
        VK.execute(new VKGetNewsFeedRequest(params.key, PAGE_SIZE), new VKApiCallback<VKPostInfo>() {
            @Override
            public void success(VKPostInfo vkPostInfo) {
                List<VKPostItem> vkPostItems = getPostItems(vkPostInfo);
                callback.onResult(vkPostItems, vkPostInfo.getNextFrom());
            }

            @Override
            public void fail(@NotNull VKApiExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    private List<VKPostItem> getPostItems(VKPostInfo vkPostInfo) {
        List<VKPostItem> vkPostItems = new ArrayList<>();

        for (VKPost vkPost : vkPostInfo.getPosts()) {
            for (VKSource vkSource : vkPostInfo.getSources()) {
                int sourceId = vkPost.getSourceId() < 0 ? vkPost.getSourceId() * (-1) : vkPost.getSourceId();
                if (sourceId == vkSource.getId()) {
                    vkPostItems.add(new VKPostItem(vkPost, vkSource));
                    break;
                }
            }
        }

        return vkPostItems;
    }

}

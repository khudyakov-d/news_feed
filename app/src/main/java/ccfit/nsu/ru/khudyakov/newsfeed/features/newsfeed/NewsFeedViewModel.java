package ccfit.nsu.ru.khudyakov.newsfeed.features.newsfeed;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import ccfit.nsu.ru.khudyakov.newsfeed.features.newsfeed.datasource.VKPostItemDataSource;
import ccfit.nsu.ru.khudyakov.newsfeed.features.newsfeed.datasource.VKPostItemDataSourceFactory;
import ccfit.nsu.ru.khudyakov.newsfeed.model.post.VKPostItem;

public class NewsFeedViewModel extends ViewModel {

    private LiveData<PagedList<VKPostItem>> itemPagedList;

    public NewsFeedViewModel() {
        VKPostItemDataSourceFactory dataSourceFactory = new VKPostItemDataSourceFactory();

        PagedList.Config pagedListConfig = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(VKPostItemDataSource.getPageSize())
                .build();

        itemPagedList = new LivePagedListBuilder<>(dataSourceFactory, pagedListConfig).build();
    }

    public LiveData<PagedList<VKPostItem>> getItemPagedList() {
        return itemPagedList;
    }

}

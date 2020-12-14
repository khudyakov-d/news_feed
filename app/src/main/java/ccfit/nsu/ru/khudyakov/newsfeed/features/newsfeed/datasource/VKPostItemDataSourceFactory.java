package ccfit.nsu.ru.khudyakov.newsfeed.features.newsfeed.datasource;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import ccfit.nsu.ru.khudyakov.newsfeed.model.post.VKPostItem;

public class VKPostItemDataSourceFactory extends DataSource.Factory<String, VKPostItem> {

    private MutableLiveData<PageKeyedDataSource<String, VKPostItem>> liveDataSource = new MutableLiveData<>();

    @NonNull
    @Override
    public DataSource<String, VKPostItem> create() {
        VKPostItemDataSource dataSource = new VKPostItemDataSource();
        liveDataSource.postValue(dataSource);
        return dataSource;
    }

}


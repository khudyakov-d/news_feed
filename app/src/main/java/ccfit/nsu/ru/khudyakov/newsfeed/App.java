package ccfit.nsu.ru.khudyakov.newsfeed;

import android.app.Application;

import com.vk.api.sdk.VK;
import com.vk.api.sdk.VKTokenExpiredHandler;

public class App extends Application {

    private final VKTokenExpiredHandler vkTokenHandler = () -> WelcomeActivity.start(App.this);

    @Override
    public void onCreate() {
        super.onCreate();
        VK.addTokenExpiredHandler(vkTokenHandler);
    }
}

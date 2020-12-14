package ccfit.nsu.ru.khudyakov.newsfeed;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.vk.api.sdk.VK;
import com.vk.api.sdk.auth.VKAccessToken;
import com.vk.api.sdk.auth.VKAuthCallback;
import com.vk.api.sdk.auth.VKScope;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.logging.Logger;

import ccfit.nsu.ru.khudyakov.newsfeed.features.newsfeed.NewsFeedActivity;

public class WelcomeActivity extends AppCompatActivity {
    Logger logger = Logger.getLogger(WelcomeActivity.class.getName());

    public static void start(final Context context) {
        Intent intent = new Intent(context, WelcomeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);

        if (VK.isLoggedIn()) {
            NewsFeedActivity.start(this);
        }

        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(v -> {
            login();
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        VKAuthCallback vkAuthCallback = new VKAuthCallback() {
            @Override
            public void onLogin(@NotNull VKAccessToken vkAccessToken) {
                NewsFeedActivity.start(WelcomeActivity.this);
            }

            @Override
            public void onLoginFailed(int i) {
                logger.info("Login failed");
                showMsg("Login failed. Please try again");
            }
        };

        if (!VK.onActivityResult(requestCode, resultCode, data, vkAuthCallback)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void login() {
        VK.login(this, Arrays.asList(VKScope.WALL, VKScope.FRIENDS, VKScope.PHOTOS));
        logger.info("Successful login");
    }

    public void showMsg(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}

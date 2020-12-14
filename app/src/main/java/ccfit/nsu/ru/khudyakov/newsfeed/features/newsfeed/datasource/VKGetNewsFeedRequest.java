package ccfit.nsu.ru.khudyakov.newsfeed.features.newsfeed.datasource;

import com.vk.api.sdk.requests.VKRequest;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import ccfit.nsu.ru.khudyakov.newsfeed.model.post.VKPostInfo;

import static ccfit.nsu.ru.khudyakov.newsfeed.utils.ParserUtils.getPosts;
import static ccfit.nsu.ru.khudyakov.newsfeed.utils.ParserUtils.getSources;

public class VKGetNewsFeedRequest extends VKRequest<VKPostInfo> {

    public VKGetNewsFeedRequest(String startFrom, int count) {
        super("newsfeed.get");

        addParam("filters", "post");

        if (startFrom != null) {
            addParam("start_from", startFrom);
        }

        addParam("count", count);
    }

    @Override
    public VKPostInfo parse(@NotNull JSONObject r) throws Exception {
        JSONObject response = r.getJSONObject("response");
        return VKPostInfo.builder()
                .posts(getPosts(response))
                .sources(getSources(response))
                .nextFrom(response.getString("next_from"))
                .build();
    }

}

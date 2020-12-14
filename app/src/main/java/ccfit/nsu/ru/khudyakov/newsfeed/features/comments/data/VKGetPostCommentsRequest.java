package ccfit.nsu.ru.khudyakov.newsfeed.features.comments.data;

import com.vk.api.sdk.requests.VKRequest;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import ccfit.nsu.ru.khudyakov.newsfeed.model.comment.VKPostComment;
import ccfit.nsu.ru.khudyakov.newsfeed.model.comment.VKPostCommentData;

import static ccfit.nsu.ru.khudyakov.newsfeed.utils.ParserUtils.getGroups;
import static ccfit.nsu.ru.khudyakov.newsfeed.utils.ParserUtils.getPhotos;
import static ccfit.nsu.ru.khudyakov.newsfeed.utils.ParserUtils.getSources;

public class VKGetPostCommentsRequest extends VKRequest<VKPostCommentData> {

    public VKGetPostCommentsRequest(int ownerId, int postId, Integer offset, int count) {
        super("wall.getComments");

        if (offset != null) {
            addParam("offset", offset);
        }

        addParam("post_id", postId);
        addParam("owner_id", ownerId);
        addParam("count", count);
        addParam("extended", 1);
    }

    @Override
    public VKPostCommentData parse(@NotNull JSONObject r) {
        try {
            JSONObject response = r.getJSONObject("response");

            return VKPostCommentData.builder()
                    .comments(getComments(response))
                    .sources(getGroups(response))
                    .sources(getSources(response))
                    .build();

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<VKPostComment> getComments(JSONObject response) {
        List<VKPostComment> vkPostComments = new ArrayList<>();

        try {
            JSONArray items = response.getJSONArray("items");

            for (int i = 0; i < items.length(); i++) {
                JSONObject post = items.getJSONObject(i);

                VKPostComment vkPostComment = VKPostComment.builder()
                        .id(post.getInt("id"))
                        .fromId(post.getInt("from_id"))
                        .date(LocalDateTime.ofInstant(
                                Instant.ofEpochSecond(post.getInt("date")),
                                TimeZone.getDefault().toZoneId())
                        )
                        .text(post.getString("text"))
                        .photos(getPhotos(post))
                        .build();

                vkPostComments.add(vkPostComment);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return vkPostComments;
    }

}

package ccfit.nsu.ru.khudyakov.newsfeed.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import ccfit.nsu.ru.khudyakov.newsfeed.model.photo.VKPhoto;
import ccfit.nsu.ru.khudyakov.newsfeed.model.post.VKPost;
import ccfit.nsu.ru.khudyakov.newsfeed.model.source.VKGroup;
import ccfit.nsu.ru.khudyakov.newsfeed.model.source.VKProfile;
import ccfit.nsu.ru.khudyakov.newsfeed.model.source.VKSource;

public class ParserUtils {

    public static List<VKPost> getPosts(JSONObject response) {
        List<VKPost> vkPosts = new ArrayList<>();

        try {
            JSONArray items = response.getJSONArray("items");

            for (int i = 0; i < items.length(); i++) {
                JSONObject post = items.getJSONObject(i);
                VKPost vkPost = getPost(post);
                if (vkPost != null) {
                    vkPosts.add(vkPost);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return vkPosts;
    }

    private static VKPost getPost(JSONObject post) {
        try {
            return VKPost.builder()
                    .sourceId(post.getInt("source_id"))
                    .postId(post.getInt("post_id"))
                    .date(LocalDateTime.ofInstant(
                            Instant.ofEpochSecond(post.getInt("date")),
                            TimeZone.getDefault().toZoneId())
                    )
                    .text(post.getString("text"))
                    .likesCount(post.getJSONObject("likes").getInt("count"))
                    .repostsCount(post.getJSONObject("reposts").getInt("count"))
                    .commentsCount(post.getJSONObject("comments").getInt("count"))
                    .photos(getPhotos(post))
                    .build();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<VKPhoto> getPhotos(JSONObject post) {
        List<VKPhoto> vkPhotos = new ArrayList<>();

        if (post.isNull("attachments")) {
            return vkPhotos;
        } else {
            try {
                JSONArray attachments = post.getJSONArray("attachments");

                for (int i = 0; i < attachments.length(); i++) {
                    JSONObject attachment = attachments.getJSONObject(i);

                    if (attachment.getString("type").equals("photo")) {
                        JSONObject photo = attachment.getJSONObject("photo");
                        JSONArray sizes = photo.getJSONArray("sizes");

                        int width = 0;
                        JSONObject maxSize = null;

                        for (int j = 0; j < sizes.length(); j++) {

                            JSONObject size = sizes.getJSONObject(j);
                            if (size.getInt("width") > width) {
                                width = size.getInt("width");
                                maxSize = size;
                            }
                        }
                        if (maxSize != null) {
                            VKPhoto vkPhoto = VKPhoto.builder()
                                    .id(photo.getInt("id"))
                                    .url(maxSize.getString("url"))
                                    .build();

                            vkPhotos.add(vkPhoto);
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return vkPhotos;
    }

    public static List<VKSource> getSources(JSONObject response) {
        List<VKSource> vkSources = new ArrayList<>();

        vkSources.addAll(getGroups(response));
        vkSources.addAll(getProfiles(response));

        return vkSources;
    }

    public static List<VKSource> getGroups(JSONObject response) {
        List<VKSource> vkGroups = new ArrayList<>();
        try {
            JSONArray groups = response.getJSONArray("groups");

            for (int i = 0; i < groups.length(); i++) {

                JSONObject group = groups.getJSONObject(i);

                VKGroup vkGroup = VKGroup.builder()
                        .id(group.getInt("id"))
                        .name(group.getString("name"))
                        .photoUrl(group.getString("photo_50"))
                        .build();

                vkGroups.add(vkGroup);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return vkGroups;
    }

    public static List<VKSource> getProfiles(JSONObject response) {
        List<VKSource> vkProfiles = new ArrayList<>();

        try {
            JSONArray profiles = response.getJSONArray("profiles");

            for (int i = 0; i < profiles.length(); i++) {

                JSONObject profile = profiles.getJSONObject(i);

                VKProfile vkProfile = VKProfile.builder()
                        .id(profile.getInt("id"))
                        .firstName(profile.getString("first_name"))
                        .lastName(profile.getString("last_name"))
                        .photoUrl(profile.getString("photo_50"))
                        .build();

                vkProfiles.add(vkProfile);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return vkProfiles;
    }
}

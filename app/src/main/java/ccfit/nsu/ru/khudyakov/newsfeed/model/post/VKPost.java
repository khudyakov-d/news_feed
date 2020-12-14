package ccfit.nsu.ru.khudyakov.newsfeed.model.post;

import java.time.LocalDateTime;
import java.util.List;

import ccfit.nsu.ru.khudyakov.newsfeed.model.photo.VKPhoto;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode(of = "sourceId")
public class VKPost {

    private Integer sourceId;

    private Integer postId;

    private LocalDateTime date;

    private String text;

    private Integer likesCount;

    private Integer repostsCount;

    private Integer commentsCount;

    private List<VKPhoto> photos;

}

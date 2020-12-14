package ccfit.nsu.ru.khudyakov.newsfeed.model.comment;

import java.util.List;

import ccfit.nsu.ru.khudyakov.newsfeed.model.source.VKSource;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class VKPostCommentData {

    private List<VKPostComment> comments;

    private List<VKSource> sources;

}

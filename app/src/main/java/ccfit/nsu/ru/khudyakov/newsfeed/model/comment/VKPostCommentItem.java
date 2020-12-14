package ccfit.nsu.ru.khudyakov.newsfeed.model.comment;

import ccfit.nsu.ru.khudyakov.newsfeed.model.source.VKSource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class VKPostCommentItem {

    private VKPostComment vkPostComment;

    private VKSource vkSource;

}

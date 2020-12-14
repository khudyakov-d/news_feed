package ccfit.nsu.ru.khudyakov.newsfeed.model.post;

import ccfit.nsu.ru.khudyakov.newsfeed.model.source.VKSource;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VKPostItem {

    private VKPost vkPost;

    private VKSource vkSource;

}

package ccfit.nsu.ru.khudyakov.newsfeed.model.post;

import java.util.List;

import ccfit.nsu.ru.khudyakov.newsfeed.model.source.VKSource;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class VKPostInfo {

    private String nextFrom;

    private List<VKPost> posts;

    private List<VKSource> sources;

}

package ccfit.nsu.ru.khudyakov.newsfeed.model.source;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class VKGroup extends VKSource {

    private String name;

}

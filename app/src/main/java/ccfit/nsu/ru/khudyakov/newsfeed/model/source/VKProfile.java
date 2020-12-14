package ccfit.nsu.ru.khudyakov.newsfeed.model.source;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class VKProfile extends VKSource {

    private String firstName;

    private String lastName;

}

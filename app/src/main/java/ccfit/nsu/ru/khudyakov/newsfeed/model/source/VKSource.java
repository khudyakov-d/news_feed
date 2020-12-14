package ccfit.nsu.ru.khudyakov.newsfeed.model.source;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@EqualsAndHashCode(of = "id")
public class VKSource {

    private Integer id;

    private String photoUrl;

}

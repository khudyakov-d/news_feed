package ccfit.nsu.ru.khudyakov.newsfeed.model.photo;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode(of = "id")
public class VKPhoto {

    private Integer id;

    private String url;

}

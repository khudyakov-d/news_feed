package ccfit.nsu.ru.khudyakov.newsfeed.model.comment;

import java.time.LocalDateTime;
import java.util.List;

import ccfit.nsu.ru.khudyakov.newsfeed.model.photo.VKPhoto;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode(of = "id")
public class VKPostComment {

    private Integer id;

    private Integer fromId;

    private LocalDateTime date;

    private String text;

    private List<VKPhoto> photos;

}

package springboot.samples.statemachine.tweet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TweetDTO {

    private String creatorId;
    private String postId;
    private String content;
    private LocalDateTime createDate;


    public static Tweet toModel(TweetDTO dto) {
        return new Tweet(null, dto.getCreatorId(), dto.getPostId(), dto.getContent(), dto.getCreateDate());
    }

}

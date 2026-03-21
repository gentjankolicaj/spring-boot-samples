package springboot.samples.statemachine.tweet;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Tweet {


  private Long id;

  private String creatorId;
  private String postId;
  private String content;
  private LocalDateTime createDate;


  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Tweet tweet)) {
      return false;
    }
    return id.equals(tweet.id);
  }
}

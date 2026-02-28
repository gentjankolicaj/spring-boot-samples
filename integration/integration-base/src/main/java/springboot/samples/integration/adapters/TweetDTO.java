
package springboot.samples.integration.adapters;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
public class TweetDTO {

  private String creatorId;
  private String postId;
  private String content;
  private LocalDateTime createDate;


  public static Tweet toModel(TweetDTO dto){
    return new Tweet(null,dto.getCreatorId(),dto.getPostId(),dto.getContent(),dto.getCreateDate());
  }

}

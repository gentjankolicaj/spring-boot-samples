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
@ToString
@Entity
@Table(name = "tweet")
public class Tweet {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(nullable = false)
  private Long id;

  private Long tweetId;
  private String creatorId;
  private String postId;
  private String content;
  private LocalDateTime createDate;


  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Tweet tweet)) {
      return false;
    }
    return tweetId.equals(tweet.tweetId);
  }
}

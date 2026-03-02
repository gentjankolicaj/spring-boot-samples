package springboot.samples.integration;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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

package springboot.samples.jdbctemplate02.domain;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

  private Long id;
  private String name;
  private LocalDate releaseDate;
}

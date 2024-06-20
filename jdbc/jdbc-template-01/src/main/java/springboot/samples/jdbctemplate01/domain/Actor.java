package springboot.samples.jdbctemplate01.domain;


import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Actor {

  private Long id;
  private String name;
  private String surname;
  private LocalDate birthday;
  private String birthplace;

}

package springboot.samples.jdbctemplate02.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

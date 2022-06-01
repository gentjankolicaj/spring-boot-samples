package springboot.samples.jdbctemplate01.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    private Long id;
    private String name;
    private LocalDate releaseDate;
}

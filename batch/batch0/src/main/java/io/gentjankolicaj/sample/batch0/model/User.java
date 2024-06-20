package io.gentjankolicaj.sample.batch0.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

  @Id
  private Integer id;
  private String name;
  private String dept;
  private Integer salary;
  private Date time;

}

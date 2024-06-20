package io.gentjankolicaj.sample.batch2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

  private int age;
  private String firstName;
  private String email;


}

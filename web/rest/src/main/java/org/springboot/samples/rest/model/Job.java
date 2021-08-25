package org.springboot.samples.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="job")
public class Job implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobId;
    private String jobTitle;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;


}

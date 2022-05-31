package springboot.samples.sqldatabases.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "job_history")
public class JobHistory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobHistoryId;
    private Long employeeId;
    private Instant startDate;
    private Instant endDate;
    private Long jobId;
    private Long departmentId;

}

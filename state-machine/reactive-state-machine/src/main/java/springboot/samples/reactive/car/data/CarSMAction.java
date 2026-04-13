package springboot.samples.reactive.car.data;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.statemachine.data.RepositoryAction;

/**
 *
 * @author gentjan kolicaj
 * @since 4/13/26 10:11 AM
 *
 */
@Data
@Entity
@Table(name = "car_state_machine_action")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class CarSMAction extends RepositoryAction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "spel")
    private String spel;
}

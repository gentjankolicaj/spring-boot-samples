package springboot.samples.reactive.car.data;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.statemachine.data.RepositoryStateMachine;

/**
 *
 * @author gentjan kolicaj
 * @since 4/13/26 10:04 AM
 *
 */
@Data
@Entity
@Table(name = "car_state_machine")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class CarStateMachine extends RepositoryStateMachine {

    @Id
    @Column(name = "machine_id")
    private String machineId;

    @Column(name = "state")
    private String state;

    @Lob
    @Column(name = "state_machine_context", length = 10240)
    private byte[] stateMachineContext;

}

package springboot.samples.reactive.car.data;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.statemachine.data.RepositoryAction;
import org.springframework.statemachine.data.RepositoryState;
import org.springframework.statemachine.state.PseudoStateKind;

import java.util.Set;

/**
 *
 * @author gentjan kolicaj
 * @since 4/13/26 10:00 AM
 *
 */
@Data
@Entity
@Table(name = "car_state_machine_state")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class CarStateMachineState extends RepositoryState {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "machine_id")
    private String machineId;

    @Column(name = "state")
    private String state;

    @Column(name = "region")
    private String region;

    @Column(name = "initial_state")
    private Boolean initial;

    @Column(name = "kind")
    private PseudoStateKind kind;

    @Column(name = "submachine_id")
    private String submachineId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_car_state_machine_state_initial_action"))
    private CarStateMachineAction initialAction;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_car_state_machine_state_parent_state"))
    private CarStateMachineState parentState;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(foreignKey = @ForeignKey(name = "fk_car_state_machine_state_state_actions_s"), inverseForeignKey = @ForeignKey(name = "fk_car_state_machine_state_state_actions_a"))
    private Set<CarStateMachineAction> stateActions;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(foreignKey = @ForeignKey(name = "fk_car_state_machine_state_entry_actions_s"), inverseForeignKey = @ForeignKey(name = "fk_car_state_machine_state_entry_actions_a"))
    private Set<CarStateMachineAction> entryActions;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(foreignKey = @ForeignKey(name = "fk_car_state_machine_state_exit_actions_s"), inverseForeignKey = @ForeignKey(name = "fk_car_state_machine_state_exit_actions_a"))
    private Set<CarStateMachineAction> exitActions;

    @ElementCollection(fetch = FetchType.EAGER, targetClass = String.class)
    @CollectionTable(name = "deferred_events", foreignKey = @ForeignKey(name = "fk_car_state_machine_state_deferred_events"))
    private Set<String> deferredEvents;

    @Override
    public Boolean isInitial() {
        return initial;
    }

    @Override
    public RepositoryAction getInitialAction() {
        return initialAction;
    }
}

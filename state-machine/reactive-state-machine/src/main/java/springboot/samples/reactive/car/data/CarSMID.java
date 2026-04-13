package springboot.samples.reactive.car.data;

import jakarta.persistence.*;
import lombok.Data;

/**
 *
 * @author gentjan kolicaj
 * @since 4/13/26 9:04 PM
 *
 */
@Data
@Entity
@Table(name = "car_state_machine_id_ref")
public class CarSMID {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String getStringId() {
        return "" + id;
    }
}

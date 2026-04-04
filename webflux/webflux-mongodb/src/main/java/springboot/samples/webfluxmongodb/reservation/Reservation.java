package springboot.samples.webfluxmongodb.reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author gentjan kolicaj
 * @since 4/4/26 11:02 AM
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Reservation {

    @Id
    private String id;
    private String name;
    private String content;
}

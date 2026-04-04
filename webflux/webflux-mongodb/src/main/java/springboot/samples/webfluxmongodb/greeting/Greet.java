package springboot.samples.webfluxmongodb.greeting;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author gentjan kolicaj
 * @since 4/4/26 11:53 AM
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Greet {

    private String id;
    private String content;
}

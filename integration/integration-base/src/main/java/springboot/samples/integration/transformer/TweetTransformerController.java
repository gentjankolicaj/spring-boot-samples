package springboot.samples.integration.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.samples.integration.Tweet;

/**
 *
 * @author gentjan kolicaj
 * @since 3/2/26 10:34 AM
 *
 */
@RestController
@RequestMapping("/integration/tweet/transformer")
public class TweetTransformerController {

    @Autowired
    TweetTransformerGateway gateway;

    @PostMapping("/add10ToIc")
    public void add10ToId(@RequestBody Tweet tweet) {
        gateway.add10ToId(tweet);
    }
}

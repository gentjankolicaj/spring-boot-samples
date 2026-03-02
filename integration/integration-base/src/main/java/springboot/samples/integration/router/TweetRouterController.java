package springboot.samples.integration.router;

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
@RequestMapping("/integration/tweet/router")
public class TweetRouterController {

    @Autowired
    TweetRouterGateway gateway;

    @PostMapping("/")
    public void to(@RequestBody Tweet tweet) {
        gateway.to(tweet);
    }

}

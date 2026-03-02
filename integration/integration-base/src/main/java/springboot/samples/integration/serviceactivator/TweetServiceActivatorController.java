package springboot.samples.integration.serviceactivator;

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
@RequestMapping("/integration/tweet/serviceactivator")
public class TweetServiceActivatorController {

    @Autowired
    TweetServiceActivatorGateway gateway;

    @PostMapping("/doProcess")
    public void doProcess(@RequestBody Tweet tweet) {
        gateway.doProcess(tweet);
    }

}

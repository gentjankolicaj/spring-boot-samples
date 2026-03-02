package springboot.samples.integration.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.samples.integration.Tweet;

/**
 *
 * @author gentjan kolicaj
 * @since 3/2/26 8:34 AM
 *
 */
@Slf4j
@RestController
@RequestMapping("/integration/filter/tweet")
public class TweetFilterController {

    @Autowired
    TweetFilterGateway tweetFilterGateway;

    @PostMapping("/notNegativeId")
    public void notNegativeId(@RequestBody Tweet tweet) {
        tweetFilterGateway.notNegativeId(tweet);
    }

}

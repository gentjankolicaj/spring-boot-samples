package springboot.samples.basereactor.pubsub;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Flow;

/**
 *
 * @author gentjan kolicaj
 * @since 3/31/26 8:53 AM
 *
 */
public class StringPublisher implements Flow.Publisher<String> {

    public List<Flow.Subscriber<? super String>> subscriberList = new LinkedList<>();

    @Override
    public void subscribe(Flow.Subscriber<? super String> subscriber) {
        this.subscriberList.add(subscriber);
    }

    public void publish(String string) {
        subscriberList.forEach(subscriber -> subscriber.onNext(string));
    }
}

package springboot.samples.kafka01_consumer.cron;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CronServiceImpl implements CronService{



    //A scheduled run of this method with fixedDelay of half a second
    @Scheduled(fixedDelay = 500)
    @Override
    public void runJob() {

    }

}

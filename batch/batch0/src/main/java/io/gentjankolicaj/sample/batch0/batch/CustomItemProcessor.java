package io.gentjankolicaj.sample.batch0.batch;

import io.gentjankolicaj.sample.batch0.model.User;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomItemProcessor implements ItemProcessor<User, User> {

  private static final Map<String, String> DEP_NAME = new HashMap<>();

  public CustomItemProcessor() {
    DEP_NAME.put("1", "Technology");
    DEP_NAME.put("2", "Finance");
  }

  @Override
  public User process(User user) throws Exception {
    String deptCode = user.getDept();
    String dept = DEP_NAME.get(deptCode);

    log.info(
        String.format("User with id [%d] converted dep from [%s] to [%s] ", user.getId(), deptCode,
            dept));

    user.setDept(dept);
    user.setTime(new Date());
    return user;
  }


}

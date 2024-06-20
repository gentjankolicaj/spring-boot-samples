package io.gentjankolicaj.sample.batch0.batch;

import io.gentjankolicaj.sample.batch0.model.User;
import io.gentjankolicaj.sample.batch0.repository.UserRepository;
import java.util.List;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomItemWriter implements ItemWriter<User> {

  private final UserRepository userRepository;

  @Autowired
  public CustomItemWriter(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public void write(List<? extends User> list) throws Exception {
    userRepository.saveAll(list);
  }

}

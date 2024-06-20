package springboot.samples.kafka02_consumer.deserializer;

import java.util.Map;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.kafka.support.serializer.DeserializationException;
import org.springframework.util.SerializationUtils;
import springboot.samples.kafka02_consumer.domain.Location;

public class LocationDeserializer implements Deserializer<Location> {

  private boolean isKey;

  @Override
  public void configure(Map<String, ?> configs, boolean isKey) {
    this.isKey = isKey;
  }

  @Override
  public Location deserialize(String topic, byte[] bytes) {
    if (bytes == null || bytes.length == 0) {
      return null;
    }
    try {
      Object object = SerializationUtils.deserialize(bytes);
      return (Location) object;
    } catch (RuntimeException e) {
      throw new DeserializationException("Error deserialization", bytes, isKey, e);
    }
  }
}

package springboot.samples.kafka02_producer.serializer;


import java.util.Map;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.util.SerializationUtils;
import springboot.samples.kafka02_producer.domain.Location;

public class LocationSerializer implements Serializer<Location> {

  private boolean isKey;

  @Override
  public void configure(Map<String, ?> configs, boolean isKey) {
    this.isKey = isKey;
  }

  @Override
  public byte[] serialize(String topic, Location location) {
    if (location == null) {
      return new byte[0];
    }
    try {
      return SerializationUtils.serialize(location);
    } catch (RuntimeException e) {
      throw new SerializationException("Error serialization ", e);
    }
  }
}

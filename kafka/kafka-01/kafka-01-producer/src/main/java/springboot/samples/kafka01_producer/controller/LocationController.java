package springboot.samples.kafka01_producer.controller;


import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springboot.samples.kafka01_producer.domain.Location;
import springboot.samples.kafka01_producer.service.LocationService;

@RestController
@RequestMapping(LocationController.LOCATION_URI)
@RequiredArgsConstructor
public class LocationController {

  static final String LOCATION_URI = "api/v1/location";

  private final LocationService locationService;

  @PostMapping
  public ResponseEntity<Location> saveLocation(@RequestBody Location location) {
    URI uri = URI.create(
        ServletUriComponentsBuilder.fromCurrentContextPath().path("api/v1/location").toUriString());
    return ResponseEntity.created(uri).body(locationService.save(location));
  }
}

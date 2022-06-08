package springboot.samples.kafka02_consumer.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springboot.samples.kafka02_consumer.domain.Location;
import springboot.samples.kafka02_consumer.service.LocationService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(LocationController.LOCATION_URI)
@RequiredArgsConstructor
public class LocationController {
    static final String LOCATION_URI="api/v1/location";

    private final LocationService locationService;


    @RequestMapping(value="/",method = RequestMethod.GET)
    public ResponseEntity<List<Location>> getAllLocations(){
        return ResponseEntity.ok(locationService.getAllLocations());
    }

    @PostMapping
    public ResponseEntity<Location> saveLocation(@RequestBody Location location){
        URI uri=URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/v1/location").toUriString());
        return ResponseEntity.created(uri).body(locationService.save(location));
    }


}

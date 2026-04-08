package springboot.samples.different.datajpa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statemachine/datajpa")
public class DataJpaController {

    private final DataJpaService dataJpaService;

    @Autowired
    public DataJpaController(DataJpaService dataJpaService) {
        this.dataJpaService = dataJpaService;
    }

    @PostMapping("/createAndPersist")
    public void createAndPersist(@RequestBody String machineKey) throws Exception {
        dataJpaService.createAndPersist(machineKey);
    }

    @PostMapping("/restoreAndUpdate")
    public void restoreAndUpdate(@RequestBody RestoreUpdateDTO restoreUpdateDTO) throws Exception {
        dataJpaService.restoreAndUpdate(restoreUpdateDTO.machineKey, restoreUpdateDTO.getEvent());
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RestoreUpdateDTO {
        String machineKey;
        DataJpaEvent event;
    }

}

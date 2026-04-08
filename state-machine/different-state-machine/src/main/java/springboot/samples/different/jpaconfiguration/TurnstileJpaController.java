package springboot.samples.different.jpaconfiguration;

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
@RequestMapping("/statemachine/turnstilejpa")
public class TurnstileJpaController {

    private final TurnstileJpaService turnstileJpaService;

    @Autowired
    public TurnstileJpaController(TurnstileJpaService turnstileJpaService) {
        this.turnstileJpaService = turnstileJpaService;
    }

    @PostMapping("/create")
    public void createAndPersist(@RequestBody String machineKey) throws Exception {
        turnstileJpaService.createAndPersist(machineKey);
    }

    @PostMapping("/restoreAndUpdate")
    public void restoreAndUpdate(@RequestBody RestoreUpdateDTO restoreUpdateDTO) throws Exception {
        turnstileJpaService.restoreAndUpdate(restoreUpdateDTO.machineKey, restoreUpdateDTO.getEvent());
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RestoreUpdateDTO {
        String machineKey;
        TurnstileJpaEvent event;
    }

}

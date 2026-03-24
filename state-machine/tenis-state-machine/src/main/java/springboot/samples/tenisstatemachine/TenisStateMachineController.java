package springboot.samples.tenisstatemachine;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tenisstatemachine/input")
public class TenisStateMachineController {

    private final TenisStateMachineService stateMachineService;


    @Autowired
    public TenisStateMachineController(TenisStateMachineService stateMachineService) {
        this.stateMachineService = stateMachineService;
    }

    @PostMapping
    public void processInput(@RequestBody String input) {
        stateMachineService.processInput(input);
    }

}

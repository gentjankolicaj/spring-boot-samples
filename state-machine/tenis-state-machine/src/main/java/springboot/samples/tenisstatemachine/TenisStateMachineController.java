package springboot.samples.tenisstatemachine;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/tenisstatemachine")
public class TenisStateMachineController {

    private final TenisStateMachineService stateMachineService;


    @Autowired
    public TenisStateMachineController(TenisStateMachineService stateMachineService) {
        this.stateMachineService = stateMachineService;
    }

    @PostMapping("/input")
    public void processInput(@RequestBody String input) {
        stateMachineService.processInput(input);
    }

    @GetMapping("/uml")
    public String generateUml() {
        return stateMachineService.generateUml();
    }

    @GetMapping(value = "/uml-image", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] generateUmlImage() throws IOException {
        return stateMachineService.generateUmlImage();
    }

}

package springboot.samples.tenisstatemachine;


import net.sourceforge.plantuml.SourceStringReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.support.AbstractStateMachine;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class TenisStateMachineService {


    private final StateMachine<TenisStateType, String> stateMachine;

    @Autowired
    public TenisStateMachineService(StateMachine<TenisStateType, String> stateMachine) {
        this.stateMachine = stateMachine;
        this.stateMachine.start();
    }

    public void processInput(String input) {
        String[] args;
        if (input == null || input.isBlank()) {
            args = new String[]{};
        } else {
            args = input.split(",");
        }
        for (String event : args) {
            stateMachine.sendEvent(event);
        }
    }


    public String generateUml() {

        // Use PlantUmlStateMachineModelFactory or a custom visitor
        // to iterate through states and transitions.
        StringBuilder sb = new StringBuilder();
        sb.append("@startuml\n");
        sb.append("[*] --> LOVE\n");
        AbstractStateMachine<TenisStateType, String> abstractStateMachine = (AbstractStateMachine<TenisStateType, String>) stateMachine;
        abstractStateMachine.getTransitions()
                .forEach(transition -> {
                    TenisStateType source = transition.getSource().getId();
                    TenisStateType target = transition.getTarget().getId();
                    String event = (transition.getTrigger() != null) ? transition.getTrigger().getEvent() : "auto";
                    sb.append(String.format("%s --> %s : %s\n", source, target, event));
                });

        sb.append("END --> [*]\n");
        sb.append("@enduml");
        return sb.toString();
    }

    public byte[] generateUmlImage() throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("@startuml\n");
        // THIS IS THE CRITICAL LINE TO FIX THE IOEXCEPTION
        sb.append("!pragma layout smetana\n");
        sb.append("[*] --> LOVE\n");

        // Iterate through transitions to build the diagram
        AbstractStateMachine<TenisStateType, String> abstractStateMachine = (AbstractStateMachine<TenisStateType, String>) stateMachine;
        abstractStateMachine.getTransitions()
                .forEach(transition -> {
                    TenisStateType source = transition.getSource().getId();
                    TenisStateType target = transition.getTarget().getId();
                    String event = (transition.getTrigger() != null) ? transition.getTrigger().getEvent() : "auto";
                    sb.append(String.format("%s --> %s : %s\n", source, target, event));
                });

        sb.append("END --> [*]\n");
        sb.append("@enduml");

        // Render the diagram to a PNG
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        SourceStringReader reader = new SourceStringReader(sb.toString());
        reader.outputImage(os);
        return os.toByteArray();
    }

}

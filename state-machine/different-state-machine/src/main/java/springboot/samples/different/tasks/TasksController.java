package springboot.samples.different.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statemachine/tasks")
public class TasksController {

  private final TasksService tasksService;

  @Autowired
  public TasksController(TasksService tasksService) {
    this.tasksService = tasksService;
  }

  @PostMapping
  public void event(@RequestBody TasksEvent turnstileEvent) {
    tasksService.event(turnstileEvent);
  }

}

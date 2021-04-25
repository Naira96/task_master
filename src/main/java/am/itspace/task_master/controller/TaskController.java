package am.itspace.task_master.controller;

import am.itspace.task_master.model.Task;
import am.itspace.task_master.service.impl.ProjectServiceImpl;
import am.itspace.task_master.service.impl.TaskServiceImpl;
import am.itspace.task_master.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class TaskController {
    private final UserServiceImpl userService;
    private final ProjectServiceImpl projectService;
    private final TaskServiceImpl taskService;

    @GetMapping("/task/add")
    public String add(ModelMap modelMap){
        modelMap.addAttribute("users", userService.users());
        modelMap.addAttribute("projects", projectService.allProjects());
        return "addTask";
    }


    @GetMapping("/allTasks")
    public String all(ModelMap modelMap){
        modelMap.addAttribute("tasks", taskService.tasks());
        return "allTasks";
    }

    @PostMapping("/task/add")
    public String save(@ModelAttribute Task task){
        taskService.addTask(task);
        log.info("task successfully added");
        return "allTasks";
    }

    @GetMapping("/task/delete")
    public String delete(@RequestParam("id") int id) {
        taskService.delete(id);
        log.info("task " + id + " was deleted");
        return "redirect:/allTasks";
    }



}

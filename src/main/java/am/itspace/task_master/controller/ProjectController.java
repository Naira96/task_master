package am.itspace.task_master.controller;

import am.itspace.task_master.exception.ResourceNotFoundException;
import am.itspace.task_master.model.Project;
import am.itspace.task_master.service.impl.ProjectServiceImpl;
import am.itspace.task_master.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ProjectController {

    private final UserServiceImpl userService;
    private final ProjectServiceImpl projectService;

    @GetMapping("/project/all")
    public String all(ModelMap modelMap) {
        modelMap.addAttribute("projects", projectService.allProjects());
        return "allProjects";
    }

    @PostMapping("/project/add")
    public String save(@ModelAttribute Project project) {
        projectService.addProject(project);
        return "redirect:/project/all";
    }


    @GetMapping("/project/add")
    public String add(ModelMap modelMap,
                             @RequestParam(value = "id", required = false) Integer id) throws ResourceNotFoundException {
        if (id != null) {
            modelMap.addAttribute("project", projectService.getById(id));
        } else {
            modelMap.addAttribute("project", new Project());
        }
        modelMap.addAttribute("users", userService.users());
        return "addProject";
    }

    @GetMapping("/project/delete")
    public String delete(@RequestParam("id") int id) {
        projectService.delete(id);
        return "redirect:/project/all";
    }
}

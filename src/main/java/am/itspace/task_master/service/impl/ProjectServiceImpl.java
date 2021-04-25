package am.itspace.task_master.service.impl;


import am.itspace.task_master.exception.ResourceNotFoundException;
import am.itspace.task_master.model.Project;
import am.itspace.task_master.repository.ProjectRepository;
import am.itspace.task_master.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    @Override
    public void addProject(Project project) {
        projectRepository.save(project);
    }

    @Override
    public List<Project> allProjects() {
        List<Project> all = projectRepository.findAll();
        return all;
    }

    @Override
    public Project getById(int id) throws ResourceNotFoundException {
        return  projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project by " + id + " does not exists"));
    }

    @Override
    public void delete(int id) {
     projectRepository.deleteById(id);
    }

    @Override
    public Project findByUserId(int id) throws ResourceNotFoundException {
        Project project = projectRepository.findByUserId(id).orElseThrow(() -> new ResourceNotFoundException("Project by " + id + " user id does not exist"));
        return project;
    }
}

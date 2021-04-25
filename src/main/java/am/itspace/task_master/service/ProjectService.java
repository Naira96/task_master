package am.itspace.task_master.service;

import am.itspace.task_master.exception.ResourceNotFoundException;
import am.itspace.task_master.model.Project;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService {
    void addProject(Project project);
    List<Project> allProjects();

    Project getById(int id) throws ResourceNotFoundException;

    void delete(int id);
    Project findByUserId(int id) throws ResourceNotFoundException;
}

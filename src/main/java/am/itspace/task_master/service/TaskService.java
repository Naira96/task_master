package am.itspace.task_master.service;


import am.itspace.task_master.exception.ResourceNotFoundException;
import am.itspace.task_master.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {

    void addTask(Task task);

    List<Task> tasks();

    Task getById(int id) throws ResourceNotFoundException;

    void delete(int id);

//    List<Task> byStatus(String status);
}

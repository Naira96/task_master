package am.itspace.task_master.service.impl;


import am.itspace.task_master.exception.ResourceNotFoundException;
import am.itspace.task_master.model.Task;
import am.itspace.task_master.repository.TaskRepository;
import am.itspace.task_master.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Override
    public void addTask(Task task){
        log.info("task {} ", task);
        taskRepository.save(task);
    }

    @Override
    public List<Task> tasks() {
        List<Task> all = taskRepository.findAll();
        return all;
    }

    @Override
    public Task getById(int id) throws ResourceNotFoundException {
        Task byId = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task with + " + id + " does not exists"));
        return byId;
    }

    @Override
    public void delete(int id) {
        taskRepository.deleteById(id);
    }

//    @Override
//    public List<Task> byStatus(String status) {
//        List<Task> allByStatus = taskRepository.findAllByStatus(status);
//        return allByStatus;
//    }
}

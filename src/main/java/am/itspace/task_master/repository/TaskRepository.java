package am.itspace.task_master.repository;


import am.itspace.task_master.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Integer> {



}

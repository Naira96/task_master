package am.itspace.task_master.repository;

import am.itspace.task_master.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project,Integer> {

    Optional<Project> findByUserId(int id);

}

package am.itspace.task_master.repository;

import am.itspace.task_master.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);


    List<User> findAllByNameStartingWith(String name);
}

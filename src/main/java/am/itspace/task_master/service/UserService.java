package am.itspace.task_master.service;


import am.itspace.task_master.exception.ResourceNotFoundException;
import am.itspace.task_master.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    void saveUser(User user);

    List<User> users();

    User getById(int id) throws ResourceNotFoundException;

    void delete(int id);

    Optional<User> findByEmail(String email);

    List<User> findAllByName(String name);

}

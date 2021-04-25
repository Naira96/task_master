package am.itspace.task_master.service.impl;



import am.itspace.task_master.exception.ResourceNotFoundException;
import am.itspace.task_master.model.User;
import am.itspace.task_master.repository.UserRepository;
import am.itspace.task_master.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> findAllByName(String name) {
        return userRepository.findAllByNameStartingWith(name);
    }

    @Override
    public List<User> users() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public User getById(int id) throws ResourceNotFoundException {
        userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User by" + id + " doesn't exist"));
        User user = userRepository.getOne(id);
        return user;
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }

}

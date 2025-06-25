package ch.noseryoung.restfoodsbackend22024.service;

import ch.noseryoung.restfoodsbackend22024.model.User;
import ch.noseryoung.restfoodsbackend22024.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    private static final List<String> ADMIN_USERNAMES = List.of("moreno", "sophia", "eleonora", "jonathan");

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        if (user.getRole() == null) {
            if (ADMIN_USERNAMES.contains(user.getLogin().toLowerCase())) {
                user.setRole(User.Role.ADMIN);
            } else {
                user.setRole(User.Role.CUSTOMER);
            }
        }
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}

package en.ratings.own.my.service.repository;

import en.ratings.own.my.model.User;
import en.ratings.own.my.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRepositoryService {
    private final UserRepository userRepository;

    @Autowired
    public UserRepositoryService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}

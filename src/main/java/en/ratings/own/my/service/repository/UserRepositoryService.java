package en.ratings.own.my.service.repository;

import en.ratings.own.my.exception.user.UserByEmailNotFoundException;
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

    public User findByEmail(String email) throws Exception {
        Optional<User> userResult = userRepository.findByEmail(email);
        if (userResult.isEmpty()) {
            throw new UserByEmailNotFoundException(email);
        }
        return userResult.get();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }
}

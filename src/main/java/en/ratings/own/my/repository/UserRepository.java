package en.ratings.own.my.repository;

import en.ratings.own.my.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Long> {
    User findByEmail(String email);
    <S extends User> S save(S user);
}

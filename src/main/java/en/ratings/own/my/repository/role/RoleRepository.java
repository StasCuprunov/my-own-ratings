package en.ratings.own.my.repository.role;

import en.ratings.own.my.model.role.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, Long> {

    Optional<Role> findById(Long id);

    Optional<Role> findByName(String name);

    <S extends Role> S save(S role);
}

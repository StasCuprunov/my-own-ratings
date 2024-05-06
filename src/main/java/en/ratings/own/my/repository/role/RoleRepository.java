package en.ratings.own.my.repository.role;

import en.ratings.own.my.model.role.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;
@Repository
public interface RoleRepository extends MongoRepository<Role, String> {

    Optional<Role> findById(String id);

    Optional<Role> findByName(String name);

    ArrayList<Role> findAll();

    <S extends Role> S save(S role);
}

package en.ratings.own.my.repository.role;

import en.ratings.own.my.model.role.RoleAssignment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleAssignmentRepository extends MongoRepository<RoleAssignment, Long> {
    Optional<List<RoleAssignment>> findAllByUserId(Long userId);

    <S extends RoleAssignment> S save(S roleAssignment);

    void deleteByUserIdAndRoleId(Long userId, Long roleId);
}

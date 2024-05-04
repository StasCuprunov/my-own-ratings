package en.ratings.own.my.repository.role;

import en.ratings.own.my.model.role.RoleAssignment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleAssignmentRepository extends MongoRepository<RoleAssignment, Long> {
    Optional<RoleAssignment> findByUserId(Long userId);

    <S extends RoleAssignment> S save(S roleAssignment);

    Optional<RoleAssignment> deleteByUserIdAndRoleId(Long userId, Long roleId);
}

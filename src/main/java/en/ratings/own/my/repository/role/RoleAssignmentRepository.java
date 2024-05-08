package en.ratings.own.my.repository.role;

import en.ratings.own.my.model.role.RoleAssignment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface RoleAssignmentRepository extends MongoRepository<RoleAssignment, String> {
    ArrayList<RoleAssignment> findAllByUserId(String userId);

    <S extends RoleAssignment> S save(S roleAssignment);

    void deleteByUserIdAndRoleId(String userId, String roleId);
}

package en.ratings.own.my.service.repository.role;

import en.ratings.own.my.model.role.RoleAssignment;
import en.ratings.own.my.repository.role.RoleAssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleAssignmentRepositoryService {

    private final RoleAssignmentRepository roleAssignmentRepository;

    @Autowired
    public RoleAssignmentRepositoryService(RoleAssignmentRepository roleAssignmentRepository) {
        this.roleAssignmentRepository = roleAssignmentRepository;
    }

    public RoleAssignment save(RoleAssignment roleAssignment) {
        return roleAssignmentRepository.save(roleAssignment);
    }
}

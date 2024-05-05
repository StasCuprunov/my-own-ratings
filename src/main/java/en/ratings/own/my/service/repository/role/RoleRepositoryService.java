package en.ratings.own.my.service.repository.role;

import en.ratings.own.my.model.role.Role;
import en.ratings.own.my.repository.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleRepositoryService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleRepositoryService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }
}

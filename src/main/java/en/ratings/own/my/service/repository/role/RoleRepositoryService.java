package en.ratings.own.my.service.repository.role;

import en.ratings.own.my.exception.role.RoleByNameNotFoundException;
import en.ratings.own.my.exception.role.RoleByIdNotFoundException;
import en.ratings.own.my.model.role.Role;
import en.ratings.own.my.repository.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class RoleRepositoryService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleRepositoryService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findByName(String name) throws Exception {
        Optional<Role> roleResult = roleRepository.findByName(name);

        if (roleResult.isEmpty()) {
            throw new RoleByNameNotFoundException(name);
        }
        return roleResult.get();
    }

    public Role findById(String id) throws Exception {
        Optional<Role> roleResult = roleRepository.findById(id);

        if (roleResult.isEmpty()) {
            throw new RoleByIdNotFoundException(id);
        }
        return roleResult.get();
    }

    public ArrayList<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }
}

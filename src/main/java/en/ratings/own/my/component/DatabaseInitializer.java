package en.ratings.own.my.component;

import en.ratings.own.my.enums.RoleEnum;
import en.ratings.own.my.model.role.Role;
import en.ratings.own.my.service.repository.role.RoleRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DatabaseInitializer implements ApplicationRunner {

    private final RoleRepositoryService roleRepositoryService;

    @Autowired
    public DatabaseInitializer(RoleRepositoryService roleRepositoryService) {
        this.roleRepositoryService = roleRepositoryService;
    }

    @Override
    public void run(ApplicationArguments args) {
        initializeRoleRepository();
    }

    private void initializeRoleRepository() {
        if (isRoleRepositoryEmpty()) {
            System.out.println("Roles have been successfully added to the database.");
            Arrays.asList(RoleEnum.values()).forEach(role -> roleRepositoryService.save(new Role(role.name())));
        }
    }

    private boolean isRoleRepositoryEmpty() {
        return roleRepositoryService.findAll().isEmpty();
    }
}

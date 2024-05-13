package en.ratings.own.my.test.integration.service;

import en.ratings.own.my.test.integration.AbstractIntegrationTest;
import en.ratings.own.my.enums.RoleEnum;
import en.ratings.own.my.model.role.Role;
import en.ratings.own.my.service.repository.role.RoleRepositoryService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

import static en.ratings.own.my.test.constant.TestConstants.EXPECTED_ONE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RoleRepositoryServiceIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private RoleRepositoryService roleRepositoryService;

    @Test
    public void testIfAllRolesFromEnumAreExistentAndUnique() {
        ArrayList<Role> roles = roleRepositoryService.findAll();
        ArrayList<String> errorMessages = new ArrayList<>();
        for (RoleEnum roleEnum: RoleEnum.values()) {
            String name = roleEnum.name();
            int numberOfEntriesFound = 0;
            for (Role role: roles) {
                if (role.getName().equals(name)) {
                    numberOfEntriesFound++;
                }
            }
            if (numberOfEntriesFound != EXPECTED_ONE) {
                errorMessages.add(errorMessage(name, numberOfEntriesFound));
            }
        }

        if (!errorMessages.isEmpty()) {
            System.out.println("The role repository is not valid because:");
            for (String errorMessage: errorMessages) {
                System.out.println(errorMessage);
            }
        }
        assertThat(errorMessages.isEmpty()).isTrue();
    }

    private String errorMessage(String roleName, int numberOfEntriesFound) {
        return "Role with name " + roleName + " has " + numberOfEntriesFound + "entries in database.";
    }
}

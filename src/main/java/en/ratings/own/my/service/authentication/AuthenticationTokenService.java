package en.ratings.own.my.service.authentication;

import en.ratings.own.my.model.User;
import en.ratings.own.my.model.role.RoleAssignment;
import en.ratings.own.my.service.repository.role.RoleAssignmentRepositoryService;
import en.ratings.own.my.service.repository.role.RoleRepositoryService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

import static en.ratings.own.my.constant.PermissionConstants.LIST_OF_GRANTED_AUTHORITIES;
import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Service
public class AuthenticationTokenService {

    private final RoleAssignmentRepositoryService roleAssignmentRepositoryService;

    private final RoleRepositoryService roleRepositoryService;

    public AuthenticationTokenService(RoleAssignmentRepositoryService roleAssignmentRepositoryService,
                                      RoleRepositoryService roleRepositoryService) {
        this.roleAssignmentRepositoryService = roleAssignmentRepositoryService;
        this.roleRepositoryService = roleRepositoryService;
    }

    public UsernamePasswordAuthenticationToken createAuthenticationToken(User user) {
        return new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(),
                getAuthorities(user.getId()));
    }

    public void setAuthentication(UsernamePasswordAuthenticationToken authenticationToken) {
        getContext().setAuthentication(authenticationToken);
    }
    private Collection<GrantedAuthority> getAuthorities(String userId) {
        Collection<GrantedAuthority> listOfAuthorities = new ArrayList<>();

        ArrayList<RoleAssignment> roleAssignments = roleAssignmentRepositoryService.findAllByUserId(userId);
        for (RoleAssignment roleAssignment: roleAssignments) {
            String roleName = roleRepositoryService.findById(roleAssignment.getRoleId()).get().getName();
            listOfAuthorities.add(LIST_OF_GRANTED_AUTHORITIES.get(roleName));
        }
        return listOfAuthorities;
    }

}

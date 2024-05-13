package en.ratings.own.my;

import en.ratings.own.my.controller.AuthenticationController;
import en.ratings.own.my.controller.UserController;
import en.ratings.own.my.dto.LoginDTO;
import en.ratings.own.my.model.User;
import en.ratings.own.my.repository.UserRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static en.ratings.own.my.test.integration.utility.CreateUserUtility.createUserFalakNoorahKhoury;
import static en.ratings.own.my.test.integration.utility.CreateUserUtility.createUserStevenWorm;
import static en.ratings.own.my.test.integration.utility.HttpResponseUtility.createHttpServletResponse;
import static org.springframework.security.core.context.SecurityContextHolder.clearContext;


/**
 * Docker must be started for testing
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Testcontainers
public abstract class AbstractIntegrationTest {

    private static final String MONGO_VERSION = "7.0.5";

    private static final String DOCKER_IMAGE_NAME = "mongo:" + MONGO_VERSION;

    private static final String URI_KEY = "spring.data.mongodb.uri";

    protected User userStevenWorm;

    protected User userFalakNoorahKhoury;

    @Autowired
    protected UserController userController;

    @Autowired
    protected AuthenticationController authenticationController;

    @Autowired
    protected UserRepository userRepository;

    @Container
    private static final MongoDBContainer MONGO_DB = new MongoDBContainer(DOCKER_IMAGE_NAME);

    @DynamicPropertySource
    private static void setProperties(final DynamicPropertyRegistry registry) {
        MONGO_DB.start();
        registry.add(URI_KEY, MONGO_DB::getReplicaSetUrl);
    }

    protected final String ID_TEST = "test";

    protected String createNotExistentId(String existentId) {
        return existentId +  ID_TEST;
    }

    @Before
    public void setup() {
        userStevenWorm = storeUserInDatabase(createUserStevenWorm());
        userFalakNoorahKhoury = storeUserInDatabase(createUserFalakNoorahKhoury());
    }

    protected void login(User user) {
        try {
            authenticationController.login(new LoginDTO(user.getEmail(), user.getPassword()),
                    createHttpServletResponse());
        } catch (Exception e) {

        }
    }

    protected void logout() {
        clearContext();
    }

    protected User storeUserInDatabase(User user) {
        String rawPassword = user.getPassword();
        try {
            userController.create(user);
        } catch (Exception ignored) {

        }
        user.setPassword(rawPassword);
        return user;
    }

    protected void deleteAllUserRepository() {
        userRepository.deleteAll();
    }
}

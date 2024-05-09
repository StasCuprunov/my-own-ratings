package en.ratings.own.my.test.integration;

import en.ratings.own.my.AbstractIntegrationTest;
import en.ratings.own.my.model.User;
import en.ratings.own.my.repository.UserRepository;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @After
    public void cleanDb() {
        userRepository.deleteAll();
    }

    @Test
    public void testBegin() {
        List<User> users = userRepository.findAll();
        assertTrue(users.isEmpty());
    }

    @Test
    public void testCreateUser() {
        User user = new User("mongo@t-online.de", "Mongo", "Mongo", "Password");
        User userResult = userRepository.save(user);
        assertEquals(user.getEmail(), userResult.getEmail());

        List<User> users = userRepository.findAll();
        assertFalse(users.isEmpty());
    }

    @Test
    public void testAfterClean() {
        List<User> users = userRepository.findAll();
        assertTrue(users.isEmpty());
    }
}

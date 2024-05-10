package en.ratings.own.my;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


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

    @Container
    private static final MongoDBContainer MONGO_DB = new MongoDBContainer(DOCKER_IMAGE_NAME);

    @DynamicPropertySource
    private static void setProperties(final DynamicPropertyRegistry registry) {
        MONGO_DB.start();
        registry.add(URI_KEY, MONGO_DB::getReplicaSetUrl);
    }
}

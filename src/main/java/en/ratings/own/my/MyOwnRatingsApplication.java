package en.ratings.own.my;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import static en.ratings.own.my.Configuration.PROFILE_DEV;

@SpringBootApplication
public class MyOwnRatingsApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .profiles(PROFILE_DEV)
                .sources(MyOwnRatingsApplication.class)
                .run(args);
    }

}

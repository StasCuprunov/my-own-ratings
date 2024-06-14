package en.ratings.own.my;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class MyOwnRatingsApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .profiles()
                .sources(MyOwnRatingsApplication.class)
                .run(args);
    }

}

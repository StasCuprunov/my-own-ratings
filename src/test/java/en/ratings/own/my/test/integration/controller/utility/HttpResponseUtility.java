package en.ratings.own.my.test.integration.controller.utility;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.mock.web.MockHttpServletResponse;

public class HttpResponseUtility {

    public static HttpServletResponse createHttpServletResponse() {
        return new MockHttpServletResponse();
    }
}

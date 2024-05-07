package en.ratings.own.my.constant;

public class RoutingConstants {

    public static final String ROUTING_ID = "/{id}";

    public static final String ROUTING_EMAIL = "/{email}";

    public static final String ROUTING_CREATE = "/create";

    public static final String ROUTING_GET = "/get" + ROUTING_ID;

    public static final String ROUTING_GET_BY_EMAIL = "/get-by-email" + ROUTING_EMAIL;

    public static final String ROUTING_EDIT = "/edit" + ROUTING_ID;

    public static final String ROUTING_DELETE = "/delete" + ROUTING_ID;

    public static final String ROUTING_DEFAULT = "/";

    public static final String ROUTING_LOGIN = "/login";

    public static final String ROUTING_LOGOUT = "/logout";

    public static final String ROUTING_START_PAGE = "/start-page";

    public static final String ROUTING_USER = "/user";

    public static final String ROUTING_RATINGS = "/ratings";

    public static final String ROUTING_RATING_ENTRIES = "/rating-entries";
}

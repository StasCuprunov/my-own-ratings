package en.ratings.own.my.constant.routing;

import static en.ratings.own.my.constant.routing.PathConstants.CREATE_PATH;
import static en.ratings.own.my.constant.routing.PathConstants.DELETE_PATH;
import static en.ratings.own.my.constant.routing.PathConstants.EDIT_PATH;
import static en.ratings.own.my.constant.routing.PathConstants.EMAIL_PATH;
import static en.ratings.own.my.constant.routing.PathConstants.ID_PATH;

public class RoutingConstants {

    public static final String ROUTING_DEFAULT = "/";

    public static final String ROUTING_LOGIN = "/login";

    public static final String ROUTING_LOGOUT = "/logout";

    public static final String ROUTING_START_PAGE = "/start-page";

    public static final String ROUTING_USER = "/user";

    public static final String ROUTING_USER_EMAIL = ROUTING_USER + EMAIL_PATH;

    public static final String ROUTING_USER_CREATE = ROUTING_USER + CREATE_PATH;

    public static final String ROUTING_RATING = "/rating";

    public static final String ROUTING_RATING_ID = ROUTING_RATING + ID_PATH;

    public static final String ROUTING_RATING_CREATE = ROUTING_RATING + CREATE_PATH;

    public static final String ROUTING_RATING_EDIT = ROUTING_RATING + EDIT_PATH;

    public static final String ROUTING_RATING_DELETE = ROUTING_RATING + DELETE_PATH;

    public static final String ROUTING_RATING_ENTRY = "/rating-entry";

    public static final String ROUTING_RATING_ENTRY_CREATE = ROUTING_RATING_ENTRY + CREATE_PATH;

    public static final String ROUTING_RATING_ENTRY_EDIT  = ROUTING_RATING_ENTRY + EDIT_PATH;

    public static final String ROUTING_RATING_ENTRY_DELETE = ROUTING_RATING_ENTRY + DELETE_PATH;
}

import LogoutIcon from '@mui/icons-material/Logout';
import HomeIcon from '@mui/icons-material/Home';
import AddCircleOutlineIcon from '@mui/icons-material/AddCircleOutline';

import {
    WEBSITE_ROUTING_INDEX,
    WEBSITE_ROUTING_LOGOUT,
    WEBSITE_ROUTING_RATINGS_CREATE
} from "../../../constant/routing/WebsiteRoutingConstants";
export const getLogoutProps = ()=> {
    return {
        to: WEBSITE_ROUTING_LOGOUT,
        text: "Logout",
        icon: LogoutIcon
    };
};

export const getStartPageProps = () => {
    return {
        to: WEBSITE_ROUTING_INDEX,
        text: "Start page",
        icon: HomeIcon
    };
};

export const getCreateRatingProps = () => {
    return {
        to: WEBSITE_ROUTING_RATINGS_CREATE,
        text: "Create rating",
        icon: AddCircleOutlineIcon
    };
};

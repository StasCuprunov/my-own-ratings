import {FunctionComponent, useEffect} from "react";
import {useLogout} from "./LogoutFunctions";
import {NavigateHandling} from "../../component/NavigateHandling";
import {deleteCookieHasLoggedInRecentlyAsRole} from "../../utility/CookieUtility";
import {WEBSITE_ROUTING_INDEX} from "../../constant/WebsiteRoutingConstants";
import {useAuth} from "../../context/AuthContext";

export const Logout: FunctionComponent<any> = () => {
    const {data, error} = useLogout();
    const {setAuthenticated} = useAuth();

    useEffect(() => {
        deleteCookieHasLoggedInRecentlyAsRole();
        setAuthenticated(false);
    }, []);

    return (
        <NavigateHandling data={data} error={error} link={WEBSITE_ROUTING_INDEX}/>
    );
};
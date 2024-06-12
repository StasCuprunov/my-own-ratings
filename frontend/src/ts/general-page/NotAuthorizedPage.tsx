import {FunctionComponent} from "react";
import {ButtonLinkWithIcon} from "../component/atom/button/link/ButtonLinkWithIcon";
import {WEBSITE_ROUTING_LOGIN} from "../constant/routing/WebsiteRoutingConstants";
import {CSS_CLASS_CONTAINER, CSS_CLASS_WEBSITE_CONTAINER} from "../constant/CSSClassNameConstants";
import LoginIcon from "@mui/icons-material/Login";

const login: any = {
    to: WEBSITE_ROUTING_LOGIN,
    text: "Go to login",
    icon: LoginIcon
};
export const NotAuthorizedPage: FunctionComponent<any> = () => {
    return (
        <div className={CSS_CLASS_WEBSITE_CONTAINER}>
            <div className={CSS_CLASS_CONTAINER}>
                <div className={CSS_CLASS_CONTAINER}>
                    <h1>Not authorized</h1>
                    <p>You have to log in to use this page.</p>
                </div>
                <ButtonLinkWithIcon props={login}/>
            </div>
        </div>
    );
};
import {FunctionComponent} from "react";
import {ButtonLink} from "../component/atom/button/link/ButtonLink";
import {WEBSITE_ROUTING_LOGIN} from "../constant/routing/WebsiteRoutingConstants";
import {CSS_CLASS_WEBSITE_CONTAINER} from "../constant/CSSClassNameConstants";

const login: any = {
    to: WEBSITE_ROUTING_LOGIN,
    text: "Login"
};
export const NotAuthorizedPage: FunctionComponent<any> = () => {
    return (
        <div className={CSS_CLASS_WEBSITE_CONTAINER}>
            <h1>Not authorized</h1>
            <p>You have to log in to use this page.</p>
            <ButtonLink props={login}/>
        </div>
    );
};
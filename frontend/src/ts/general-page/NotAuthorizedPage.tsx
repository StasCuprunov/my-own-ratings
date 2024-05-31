import {FunctionComponent} from "react";
import {ButtonLink} from "../component/atom/button/link/ButtonLink";
import {WEBSITE_ROUTING_LOGIN} from "../constant/routing/WebsiteRoutingConstants";

const login: any = {
    to: WEBSITE_ROUTING_LOGIN,
    text: "Login"
};
export const NotAuthorizedPage: FunctionComponent<any> = () => {
    return (
        <div>
            <h1>Not authorized</h1>
            <p>You have to log in to use this page.</p>
            <ButtonLink props={login}/>
        </div>
    );
};
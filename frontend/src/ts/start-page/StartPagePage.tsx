import {FunctionComponent} from "react";
import {CreateButtonLink} from "../component/atom/button/CreateButtonLink";
import {WEBSITE_ROUTING_RATINGS_CREATE} from "../constant/routing/WebsiteRoutingConstants";

const createButtonLink: any = {
    to: WEBSITE_ROUTING_RATINGS_CREATE
}

export const StartPagePage: FunctionComponent<any> = ({name, ratingDTOs}) => {

    return (
        <div>
            <h1>Hello {name}!</h1>
            <div>
                <h2>Create a new rating</h2>
                <CreateButtonLink props={createButtonLink}/>
            </div>
            <div>
                <h2>Your ratings</h2>
            </div>
        </div>
    );
};
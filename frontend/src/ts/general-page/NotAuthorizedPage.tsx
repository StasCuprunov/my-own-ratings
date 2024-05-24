import {FunctionComponent} from "react";

export const NotAuthorizedPage: FunctionComponent<any> = () => {
    return (
        <div>
            <h1>Not authorized</h1>
            <p>You have to log in to use this page.</p>
        </div>
    );
};
import {FunctionComponent} from "react";

export const ErrorPage: FunctionComponent<any> = ({message, description}) => {
    return (
        <div>
            <h1>{message}</h1>
            <ul>
                <li>{description}</li>
            </ul>
        </div>
    );
};
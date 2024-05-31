import {FunctionComponent} from "react";
import {Button} from "../../component/atom/button/Button";

export const ErrorPage: FunctionComponent<any> = ({message, description, reloadButtonProps}) => {
    return (
        <div>
            <h1>{message}</h1>
            <ul>
                <li>{description}</li>
            </ul>
            <Button props={reloadButtonProps}/>
        </div>
    );
};
import {FunctionComponent} from "react";
import {CustomButton} from "../../component/atom/button/CustomButton";

export const ErrorPage: FunctionComponent<any> = ({message, description, reloadButtonProps}) => {
    return (
        <div>
            <h1>{message}</h1>
            <ul>
                <li>{description}</li>
            </ul>
            <CustomButton props={reloadButtonProps}/>
        </div>
    );
};
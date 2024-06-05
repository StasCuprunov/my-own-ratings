import {FunctionComponent} from "react";
import {CustomButton} from "../../component/atom/button/CustomButton";

export const ErrorPage: FunctionComponent<any> = ({props}) => {
    return (
        <>
            <h1>{props.message}</h1>
            <ul>
                <li>{props.description}</li>
            </ul>
            <CustomButton props={props.reloadButtonProps}/>
        </>
    );
};
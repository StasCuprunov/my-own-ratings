import {FunctionComponent} from "react";

export const Registration: FunctionComponent<any> = ({props}) => {
    return (
        <div>
            <h1>Registration</h1>
            <h2>{props.passwordMinimumLength}</h2>
        </div>
    );
}
